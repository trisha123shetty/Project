package String;
import java.util.Scanner;
public class CountOwels {
	public static boolean isVowel(char word) {
		if(word=='a'|| word=='e'||word=='i'||word=='o'||word=='u')
		{
		return true;
		}
		if(word=='A'|| word=='E'||word=='I'||word=='O'||word=='U')
		{
		return true;
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the string");
		String st=sc.nextLine();
		int count=0;
		int n=st.length();
		for(int i=0;i<n;i++) {
			char word=st.charAt(i);
			if(isVowel(word) == true) {
				count++;
			}
			
					}
		System.out.println("count of owels is"+count);
	}

}
