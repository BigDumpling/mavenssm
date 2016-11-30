import org.springframework.stereotype.Service;

/**
 * Created by ligq01 on 2016/11/9.
 */

@Service
public class config {
	public String sayHello(String word){
		return "Hello " + word + "!";
	}
}
