package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate template;


    @MessageMapping("/hello")
 //  @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Greeting greeting = new Greeting(message.getName() + " : " + message.getMsg());
        template.convertAndSend( "/topic/greetings", greeting);
        return greeting;
    }


    @RequestMapping("/msg")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name) {

        template.convertAndSend( "/topic/greetings", new Greeting("booom"));
        return "greeting";
    }

}
