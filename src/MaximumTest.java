public class MaximumTest
{
   // �Ƚ�����ֵ���������ֵ
   public static <E extends Comparable<E>> E maximum(E x, E y, E z)
   {                     
      E max = x; // ����x�ǳ�ʼ���ֵ
      if ( y.compareTo( max ) < 0 ){
         max = y; //y ����
      }
      if ( z.compareTo( max ) < 0 ){
         max = z; // ���� z ����           
      }
      return max; // ����������
   }
   public static void main( String args[] )
   {
      System.out.printf( "%d, %d �� %d ��������Ϊ %d\n\n",
                   3, 4, 5, maximum( 3, 4, 5 ) );
 
      System.out.printf( "%.1f, %.1f �� %.1f ��������Ϊ %.1f\n\n",
                   6.6, 8.8, 7.7, maximum( 6.6, 8.8, 7.7 ) );
 
      System.out.printf( "%s, %s �� %s ��������Ϊ %s\n","pear",
         "apple", "orange", maximum( "pear", "apple", "orange" ) );
   }
}