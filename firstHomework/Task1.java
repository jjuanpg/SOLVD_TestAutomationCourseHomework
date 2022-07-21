package firstHomework;
import java.util.*;

public class Task1 {

	public static void main(String[] args) {
		System.out.println("Enter some text: ");
		Scanner sc = new Scanner(System.in);
		String text = sc.next();
		sc.close();
		
		System.out.println(text);
		for(int i=0; i<args.length; i++){
			System.out.println(args[i]);
		}
	}

}
