package employee;
// �ļ����� CheckingAccount.java
import java.io.*;
 
//����ģ�������˻�
public class CheckingAccount
{
  //balanceΪ��numberΪ����
   private double balance;
   private int number;
   public CheckingAccount(int number)
   {
      this.number = number;
   }
  //��������Ǯ
   public void deposit(double amount)
   {
      balance += amount;
   }
  //������ȡǮ
   public void withdraw(double amount) throws
                              InsufficientFundsException
   {
      if(amount <= balance)
      {
         balance -= amount;
      }
      else
      {
         double needs = amount - balance;
         throw new InsufficientFundsException(needs);
      }
   }
  //�������������
   public double getBalance()
   {
      return balance;
   }
  //���������ؿ���
   public int getNumber()
   {
      return number;
   }
}

public class InsufficientFundsException extends Exception
{
  //�˴���amount�������浱�����쳣��ȡ��Ǯ�������ʱ����ȱ����Ǯ
  private double amount;
  public InsufficientFundsException(double amount)
  {
    this.amount = amount;
  } 
  public double getAmount()
  {
    return amount;
  }
}