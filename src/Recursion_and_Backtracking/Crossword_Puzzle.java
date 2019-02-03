package Recursion_and_Backtracking;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Crossword_Puzzle {

    static boolean placeWordHorizontally(char[][] ch, String word,char limitter)
{
    int len=word.length();
      for(int i=0;i<10;i++)
      {
          int j=0;
          inner:while(j<=10-len)
          {
              int k=j;
              for(k=j;k<j+len;k++)
                  if(ch[i][k]==limitter)break;

              if(k==j+len)
              {
                  if((k<=9 && ch[i][k]!=limitter)||(j>0 && ch[i][j-1]!=limitter))
                  {
                      j++;
                      continue;
                  }
                  //means space is free
                  for(k=j;k<j+len;k++)
                  {
                      if(ch[i][k]!='-' && word.charAt(k-j)!=ch[i][k])
                      {
                          j++;
                      continue inner; 
                      }
                  }
                  //correct place
                  for(k=j;k<j+len;k++)
                      ch[i][k]=word.charAt(k-j);
                  return true;
              }
              j++;
          }
      }
        return false;
}


    static boolean placeWordVertically(char[][] ch, String word,char limitter)
 {
    int len=word.length();
      for(int i=0;i<10;i++)
      {
          int j=0;
          inner:while(j<=10-len)
          {
              int k=j;
              for(k=j;k<j+len;k++)
                  if(ch[k][i]==limitter)break;

              if(k==j+len)
              {
                  if((k<=9 && ch[k][i]!=limitter)||(j>0 && ch[j-1][i]!=limitter))
                  {
                      j++;
                      continue;
                  }
                  //means space is free
                  for(k=j;k<j+len;k++)
                  {
                      if(ch[k][i]!='-' && word.charAt(k-j)!=ch[k][i])
                      {
                          j++;
                       continue inner;
                      }
                  }
                  //correct place
                  for(k=j;k<j+len;k++)
                      ch[k][i]=word.charAt(k-j);
                  return true;
              }
              j++;
          }
      }
        return false;
}



static void fill(char[][] ch,Map<Integer,List<String>> map,char limitter)
{
    //try filling map words one by one..
    boolean isComplete=true;
    for(Map.Entry<Integer,List<String>> entry : map.entrySet())
        if(!entry.getValue().isEmpty())
        {
            isComplete=false;
            break;
        }
    if(isComplete)return;
    char[][] backup=new char[10][10];
    for(Map.Entry<Integer,List<String>> entry : map.entrySet())
    {
        int len = entry.getKey();
        List<String> wordList = entry.getValue();
        for(String word : wordList)
        {
        if(placeWordHorizontally(ch,word,limitter) || placeWordVertically(ch,word,limitter))
            {
                //since this word is placed remove this word from map...
                List<String> newWordList = new ArrayList<>();
                for(String w : entry.getValue())
                newWordList.add(w);

                newWordList.remove(word);
                map.put(entry.getKey(),newWordList);                    
                for(int a=0;a<10;a++)
                    for(int b=0;b<10;b++)
                        backup[a][b]=ch[a][b];
                fill(ch,map,limitter);
                //if we cant place word here revert action
                newWordList.add(word);
                map.put(entry.getKey(),newWordList);
                    for(int a=0;a<10;a++)
                    for(int b=0;b<10;b++)
                        ch[a][b]=backup[a][b];
            }
        }
    }

}

// Complete the crosswordPuzzle function below.
static String[] crosswordPuzzle(String[] crossword, String input) {
    char ch[][]=new char[10][10];
    char limitter='+';
    for(int i=0;i<10;i++)
    {
        if(crossword[i].indexOf('X') > -1)
            limitter='X';
        ch[i]=crossword[i].toCharArray();
    }

    String[] words=input.split(";");
    Map<Integer,List<String>> map = new HashMap<>();
    for(String word : words)
    {
        int k = word.length();
        if(map.containsKey(k))
        {
            List<String> list = map.get(k);
            list.add(word);
            map.put(k,list);
        }
        else
        {
            List<String> list = new ArrayList<>();
            list.add(word);
            map.put(k,list);
        }
    }
    fill(ch,map,limitter);
    for(int i=0;i<10;i++)
        crossword[i]=String.valueOf(ch[i]);
    return crossword;
}

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] crossword = new String[10];

        for (int i = 0; i < 10; i++) {
            String crosswordItem = scanner.nextLine();
            crossword[i] = crosswordItem;
        }

        String words = scanner.nextLine();

        String[] result = crosswordPuzzle(crossword, words);

//        for (int i = 0; i < result.length; i++) {
//            bufferedWriter.write(result[i]);
//
//            if (i != result.length - 1) {
//                bufferedWriter.write("\n");
//            }
//        }

//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}

