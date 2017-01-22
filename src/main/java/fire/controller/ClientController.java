package fire.controller;

import fire.domain.Customer;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by rena17 on 1/22/2017.
 *
 * Controller is used to display questions
 * It displays questions and answers
 */
@Controller
public class ClientController {

  @GetMapping("/testfire")
  public String testGetRecommendedBundle(Model model, Customer customer) {

    String bundle = "";
    if ((customer != null)) {
      final String uri = "http://localhost:8080/fire?age={age}&student={student}&income={income}";
      final HashMap<String, String> uriVariables = new HashMap<String, String>(3);
      uriVariables.put("age", Integer.toString(customer.getAge()));
      uriVariables.put("student", Boolean.toString(customer.isStudent()));
      uriVariables.put("income", Integer.toString(customer.getIncome()));

      RestTemplate restTemplate = new RestTemplate();
      try {
        bundle = restTemplate.getForObject(uri, String.class, uriVariables);
      } catch (HttpClientErrorException hcee) {
        System.out.println(hcee.getStatusCode() + ": " + hcee.getResponseBodyAsString());
      }
    }

    model.addAttribute("customer", customer);
    model.addAttribute("bundle", bundle);
    return "testfire";
  }

  @GetMapping("/testbundle")
  public String testValidateBundle(Model model, Customer customer) {

    ResponseEntity<String> responseEntity = null;
    String responseCode = "";
    String responseText = "";
    if ((customer != null)) {
      final String uri = "http://localhost:8080/bundle?bundle={bundle}&age={age}&student={student}&income={income}";
      final HashMap<String, String> uriVariables = new HashMap<String, String>(4);
      uriVariables.put("bundle", customer.getBundle());
      uriVariables.put("age", Integer.toString(customer.getAge()));
      uriVariables.put("student", Boolean.toString(customer.isStudent()));
      uriVariables.put("income", Integer.toString(customer.getIncome()));

      RestTemplate restTemplate = new RestTemplate();
      try {
        responseEntity = restTemplate.getForEntity(uri, String.class, uriVariables);
        responseCode = responseEntity.getStatusCode().toString();
      } catch (HttpClientErrorException hcee) {
        System.out.println(hcee.getStatusCode() + ": " + hcee.getResponseBodyAsString());
        responseCode = hcee.getStatusCode().toString();
        responseText = hcee.getResponseBodyAsString();
      }
    }

    model.addAttribute("customer", customer);
    model.addAttribute("response", responseCode + " - " + (responseText.length() == 0 ? "(no text)" : responseText));
    return "testbundle";
  }
}
