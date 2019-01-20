import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the decibinaryNumbers function below.
    public static Integer decibinaryNumbers(Integer x)
    {
        Integer idx = x - 1;
        int n = 0;
        long count = 0;
        while (true)
        {
            List<Integer> repsForN = generateAllDbReps(n);
            if (idx >= count && idx < count + repsForN.size())
            {
                return repsForN.get((int)(idx-count));
            }
            count += repsForN.size();
            n++;
        }
    }

    public static List<Integer> generateAllDbReps(int n)
    {
        List<Integer> result = new ArrayList<Integer>();
        if (n == 0)
        {
            result.add(0);
            return result;
        }

        int place = (int)(Math.log(n)/Math.log(2));
        Integer dbNum = 0;

        internalGenerateAllDbReps(n, place, dbNum, result);
        return result;
    }

    private static void internalGenerateAllDbReps(Integer n, int place, Integer dbNum, List<Integer> result)
    {
        if (n == 0)
        {
            result.add(dbNum);
            return;
        }

        if (place < 0)
        {
            return;
        }

        Integer pp2 = (Integer)(int)Math.pow(2, place);
        Integer maxDigit = n / pp2;
        if (maxDigit > 9)
        {
            return;
        }

        Integer pp10 = (Integer)(int)Math.pow(10, place);
        for (int digit = 0; digit <= maxDigit; digit++)
        {
            internalGenerateAllDbReps(n - (digit * pp2), place-1, dbNum + (digit * pp10), result);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        
        Map<Integer, List<Integer>> data = 

        for (int qItr = 0; qItr < q; qItr++) {
            Integer x = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            Integer result = decibinaryNumbers(x);
            
            System.out.println(result);

//            bufferedWriter.write(String.valueOf(result));
//            bufferedWriter.newLine();
        }

//        bufferedWriter.close();

        scanner.close();
    }
}
