
import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyTagHandler extends SimpleTagSupport {
	private int num1;
	private int num2;
	private int num3;
	private int num4;
	private int num5;
	
	public int getNum1() {
		return num1;
	}
	public int getNum2(){
		return num2;
	}
	public int getNum3(){
		return num3;
	}
	public int getNum4(){
		return num4;
	}
	public int getNum5(){
		return num5;
	}
	
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	
	public void setNum3(int num3) {
		this.num3 = num3;
	}
	
	public void setNum4(int num4) {
		this.num4 = num4;
	}
	
	public void setNum5(int num5) {
		this.num5 = num5;
	}

	@Override
	public void doTag() throws JspException, IOException{
		JspWriter out = getJspContext().getOut();
		out.println(num1 + ", " + num2 + ", " + num3 + ", " + num4 + ", " + num5);
		
		int[] arrs = {num1, num2, num3, num4, num5};
		quick_sort(arrs, 0, arrs.length -1);
		out.print("정렬 후 : ");
		
		for(int arr : arrs) {
			out.print(arr + " ");
		}
	}
	static int partition(int[] list, int left, int right){

        int pivot;
        pivot = list[left];

        while (left < right) {
            while ((list[left] < pivot) && (left < right))
                left++;
            while ((list[right] > pivot) && (left < right))
                right--;

            if (left < right) {
                int temp = list[left];
                list[left] = list[right];
                list[right] = temp;
            }
        }

        return left;
    }


    static void quick_sort(int[] list, int left, int right){
        if(left < right){
            int q = partition(list, left, right);
            quick_sort(list, left,q - 1);
            quick_sort(list,q + 1, right);
        }
    }
}

