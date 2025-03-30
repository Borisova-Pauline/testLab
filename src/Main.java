import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество строк m [2; 5]: ");
        int m = sc.nextInt();
        while(m<2||m>5){
            System.out.println("Число вышло из диапазона допустимых значений");
            System.out.print("Введите количество строк m [2; 5]: ");
            m = sc.nextInt();
        }
        System.out.print("\nВведите количество столбцов n [2; 5]: ");
        int n = sc.nextInt();
        while(n<2||n>5){
            System.out.println("Число вышло из диапазона допустимых значений");
            System.out.print("Введите количество столбцов n [2; 5]: ");
            n = sc.nextInt();
        }
        int[][] matrix = new int[m][n];
        for(int i=0; i<m;i++){
            System.out.println("\nЗаполнение "+(i+1)+" строки [-100; 100]: ");
            for(int j=0; j<n;j++){
                matrix[i][j] = sc.nextInt();
                while(matrix[i][j]<-100||matrix[i][j]>100){
                    System.out.println("Число вышло из диапазона допустимых значений");
                    matrix[i][j] = sc.nextInt();
                }
            }
        }
        int s = 0; //счётчик отрицательных чисел
        int om=0; //индексы второго отрицательного элемента
        int on=0;
        ArrayList<Integer> tempArr = new ArrayList<>();
        System.out.println("\nМатрица до сортировки: ");
        for(int i=0; i<m;i++){
            for(int j=0; j<n;j++){
                if(s==3){
                    tempArr.add(matrix[i][j]);
                }
                if(matrix[i][j]<0 && s<2){
                    s++;
                    if(s==2){
                        om=i;
                        on=j;
                        s++;
                    }
                }
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.print("\n");
        }
        if((om==m-1 && on==n-1)){
            System.out.println("\nПосле второго отрицательного числа отсутствуют элементы. Сортировка невозможна");
        }else if(s<3){
            System.out.println("\nВ матрице отсутствует требуемое количество отрицательных чисел (два)");
        }else{
            int[] sTemp=new int[tempArr.size()];
            for(int i = 0;i<tempArr.size();i++){
                sTemp[i]=tempArr.get(i);
            }

            int k=0;//индексы sTemp
            shakerSort(sTemp);
            for(int i=om; i<m;i++){
                if(i==om){
                    for(int j=on+1; j<n;j++){
                        matrix[i][j]=sTemp[k];
                        k++;
                    }
                }else{
                    for(int j=0; j<n;j++){
                        matrix[i][j]=sTemp[k];
                        k++;
                    }
                }
            }

            System.out.println("\nМатрица после сортировки: ");
            for(int i=0; i<m;i++){
                for(int j=0; j<n;j++){
                    System.out.print(matrix[i][j]+"\t");
                }
                System.out.print("\n");
            }
        }
    }


    public static void shakerSort(int[] mas){
        int left =0;
        int right = mas.length-1;
        while(left<=right){
            for(int i=right;i>left;i--){
                if(mas[i-1]<mas[i]){
                    int temp = mas[i];
                    mas[i]=mas[i-1];
                    mas[i-1]=temp;
                }
            }
            ++left;
            for(int i=left;i<right;++i){
                if(mas[i]<mas[i+1]){
                    int temp = mas[i];
                    mas[i]=mas[i+1];
                    mas[i+1]=temp;
                }
            }
            --right;
        }
        /*System.out.print("\n");
        for(int i=0;i<mas.length;i++){
            System.out.print(mas[i]+"\t");

        }*/
    }
}