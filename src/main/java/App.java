
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * Created by Guest on 1/8/18.
 */
public class App {
    public static void main(String[] args) {
        ChangeMachine changeMachine = new ChangeMachine();
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/machine", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            System.out.println(request.queryParams());
            String amount = request.queryParams("amount");
            model.put("amount", amount);
            String newAmount = changeMachine.makeChange(Float.parseFloat(amount));
            model.put("newAmount", newAmount);
            return new ModelAndView(model, "machine.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
