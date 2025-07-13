package cognizant.springlearn;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLearnApplication {

    public static void displayCountries() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countryList = context.getBean("countryList", List.class);

        for (Country country : countryList) {
            System.out.println("Country : " + country);
        }
    }

    public static void main(String[] args) {
        System.out.println("Start of Main");
        displayCountries();
        System.out.println("End of Main");
    }
}
