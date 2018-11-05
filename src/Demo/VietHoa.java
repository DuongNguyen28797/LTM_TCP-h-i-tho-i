package Demo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Spring;

public class VietHoa {

	//private static final String StringUltis = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String chuoi1;
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap mot chuoi: ");
		chuoi1 = sc.nextLine();
		System.out.println(VietHoa(chuoi1));
	}
	
	//Viet hoa chu cai dau
	public static String VietHoa(String s)

    {
       if(s != null && s.isEmpty()){
    	   System.out.println("The function is wrong here");
    	   return s;
       }
        String result = "";

        //lấy danh sách các từ  

        String[] words = s.split(" ");
        for (String word : words) {
			if(word.trim() != "")
			{
				if(word.length() >1)
					result += word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase() + " ";
				else
					result += word.toUpperCase() + " ";
			}
		}
        return result.trim();
    }
	
	
	}

