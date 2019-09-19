
public class HW_05 {

	public static void main(String[] args) {
		System.out.println(getArea(4,5));
		System.out.println(reverse("Hello!"));
		System.out.println(find("Mississippi", "sip"));
		int [] arr = new int[] {1000,2,3,9,5,6};
		System.out.println(largestEle(arr));
	}


	//P 13.1 getArear
	public static int getArea(int width,int height) {
		if(width<1||height<1)return 0;
		return height+getArea(width-1,height);
	}

	//P 13.3 Reverse
	public static String reverse(String str) {
		if (str=="" || str.length() <= 1) {
			return str;
		}
		return reverse(str.substring(1)) + str.charAt(0);
	}

	//P 13.6 Find str
	public static boolean find(String text, String str) {
		if ((text == null) || (str == null) || text.isEmpty()) return false;
		if (text.startsWith(str))return true;
		return find(text.substring(1), str);

	}

	//P 13.8 Largest element in a array
	public static int largestEle(int[] arr){
		return largestEle(arr,arr.length-1);
	}
	private static int largestEle(int[] arr, int index) {
		if (index > 0)return Math.max(arr[index], largestEle(arr, index-1));
		return arr[0];
	}



}
