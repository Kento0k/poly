/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polynome;
import java.util.Scanner;
public class Polynome {
        public int koef[];
	public int step;
        public Polynome (int n){
            step= n;
            koef=new int[step+1];
            for(int i=0;i<=step;i++)
                koef[i]=0;
        }
    public  int input (int n){
        step=n;
        koef=new int[step+1];
	Scanner sc=new Scanner(System.in); 
        System.out.println("Insert koefficients (from left to right)");
        for(int i=0;i<n+1;i++)
        {	
            koef[i]=sc.nextInt();
        }
        return 0;
    }
    public int poly_default (){
       step= 4;
       koef= new int[5];
       koef[0]=-8;
       koef[1]=1;
       koef[2]=-6;
       koef[3]=3;
       koef[4]=7;
       output();
       return 0;
    }
    public void inputpolinoms(){ 
        input(step);
        System.out.println("Polinom 1=");
        output();
        return;
    }
    public void output (){
        int cnt;
        for(cnt= step; cnt>=0; cnt--){
            if (koef[cnt]!= 0){
                System.out.print(koef[cnt]+ "x^"+ cnt);
            }
            if((cnt>0)&&(koef[cnt-1]>0)&&(cnt-1>=0)){
                    System.out.print("+");
                }
        }
        System.out.print("\n");
        return;
    }
    public void evaluate(int val){
        int cnt, rez=0;
        for (cnt=0; cnt<step+1; cnt++){
            rez+=koef[cnt]*Math.pow(val, (step-cnt));
        }
        System.out.println("Value= "+ rez);
    }
    public void compare (Polynome poly){
        if (step== poly.step){
            int cnt;
            for(cnt=0; cnt<step+1; cnt++){
                if(koef[cnt]!=poly.koef[cnt]){
                    System.out.println("Polynomes aren`t equal");
                    return;
                }
            }
           System.out.println("Polynomes are equal"); 
        }
        else{
             System.out.println("Polynomes aren`t equal");
             return;
        }
    }
    public void summ(Polynome poly){
        int cnt;
        int rezgrad=Math.max(poly.step, step);
        Polynome result= new Polynome(rezgrad);
        for (cnt=0; cnt<rezgrad+1; cnt++){
            if (cnt<=Math.min(poly.step, step)){
                result.koef[cnt]=poly.koef[cnt]+koef[cnt];
            }
            else{
                if (poly.step>step)
                    result.koef[cnt]=poly.koef[cnt];
                else
                    result.koef[cnt]=koef[cnt];
            }
        }
        result.output();
    }
    public void diff(Polynome poly){
        int cnt;
        int rezgrad=Math.max(poly.step, step);
        Polynome result= new Polynome(rezgrad);
        for (cnt=0; cnt<rezgrad+1; cnt++){
            if (cnt<=Math.min(poly.step, step)){
                result.koef[cnt]=poly.koef[cnt]-koef[cnt];
            }
            else{
                if (poly.step>step)
                    result.koef[cnt]=poly.koef[cnt];
                else
                    result.koef[cnt]=koef[cnt];
            }
        }
        result.output();
    }
    public void comp (Polynome poly){
        int i, j;
        Polynome result= new Polynome(step+poly.step);
        for(i=0; i<step+1; i++){
            for(j=0; j<((poly.step)+1); j++){
                  result.koef[i+j]+=koef[i]*poly.koef[j];
            }
        }
        result.output();
    }
    public void div(Polynome poly){
        int i, j, k;
        Polynome result= new Polynome(poly.step-step);
        Polynome prom= new Polynome(poly.step);
        Polynome poly_copy= new Polynome(poly.step);
        poly_copy= poly;
        for (i=poly.step; i>=step; i--){
             j= step;
             System.out.println("Step= "+ i);
             if(poly_copy.koef[i]!=0){
                result.koef[i-j]=poly_copy.koef[i]/koef[j];
                for (k=0; k<=step; k++){
                    prom.koef[poly_copy.step-k]= result.koef[i-j]*koef[step-k];
                    //System.out.println("Step"+ k+ " "+ prom.koef[k]);
                }
                poly_copy.koef[i]=0;
                for (k=i-1; k>=step; k--){
                   poly_copy.koef[k]-=prom.koef[k];
                   //System.out.println("Step"+ k+ " "+ poly_copy.koef[k]);
                }
                poly_copy.step--;
             }  
        }
        result.output();
    }
    void ost (Polynome poly){
        int i, j, k;
        Polynome result= new Polynome(poly.step-step);
        Polynome prom= new Polynome(poly.step);
        Polynome poly_copy= new Polynome(poly.step);
        Polynome ostat= new Polynome (poly.step);
        poly_copy= poly;
        for (i= poly.step; i>=step; i--){
            j=step;
            if (poly_copy.koef[i]!=0){
                result.koef[i-j]=poly_copy.koef[i]/koef[j];
                for (k=step; k>=0; k--){
                    prom.koef[poly_copy.step-k]=result.koef[i-j]*koef[step-k];
                    poly_copy.koef[poly_copy.step-k]-=prom.koef[poly_copy.step-k];
                }
                ostat.koef[i]=poly_copy.koef[i];
                poly_copy.step--;
            }
        }
        for (i=step-1; i>=0; i--)
            ostat.koef[i]=poly_copy.koef[i];
        ostat.output();
    }
    public static void main(String[] args) {
        int grad=0, oper=0, eval;
        Polynome poly_def= new Polynome(4);//default polynome
        Scanner sc=new Scanner(System.in); 
        poly_def.poly_default();
        System.out.println("Vvedite stepen polinoma= ");
        grad=sc.nextInt();
        Polynome pol_1= new Polynome(grad);
        pol_1.inputpolinoms();
        System.out.println("Operations: ");
        System.out.println("1. Evaluate polynome ");
        System.out.println("2. Check polynome`s equality ");
        System.out.println("3. Polynomes summary");
        System.out.println("4. Polynomes difference");
        System.out.println("5. Polynomes composition");
        System.out.println("6. Polynomes division");
        System.out.println("7. Polynomes division remainder");
        Scanner inp =new Scanner(System.in); 
        if(inp.hasNextInt())
            oper= inp.nextInt();
        else
            System.out.println("Error!!!");
        switch(oper){
            case 1:
              System.out.println("Number of polynome: ");
              int val;
              eval= inp.nextInt(); 
              System.out.println("Value of x: ");
              val= inp.nextInt(); 
              switch(eval){
                  case 1:
                      poly_def.evaluate(val);
                      break;
                  case 2:
                      pol_1.evaluate(val);
                      break;
              }
              break;
            case 2:
                pol_1.compare(poly_def);
                break;
            case 3:
                pol_1.summ(poly_def);
                break;
            case 4:
                pol_1.diff(poly_def);
                break;
            case 5:
                pol_1.comp(poly_def);
                break;
            case 6:
                pol_1.div(poly_def);
                break;
            case 7:
                pol_1.ost(poly_def);
                break;
        }
    }
}
