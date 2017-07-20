package nl.aegon.assessment.cases;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nl.aegon.assessment.cases.model.Employee;


/**
 * Util class containing some static methods, which are used to make a point
 * 
 * @version $Id:$
 * @author wingerma (c) 8 apr. 2014, Sogeti B.V.
 */
public final class Util
{
   /**
    * Constructor: create a new Util, or not in this case :-)
    */
   private Util()
   {
      // Deliberately empty
   }

   /**
    * Get a boolean object-type, is this the way to go, or is another way more appropriate
    * 
    * @param string value which has to be converted
    * @return Boolean object corresponding to input value
    */
   public static Boolean getValue(String value)
   {
      return new Boolean(value);
   }

   /**
    * Finds an item in a set
    * 
    * @param set
    * @param item
    * @return whether the item is in the set
    */
   public static boolean findItem(Set<String> set, String item)
   {
      for (String listItem : set)
      {
         if (listItem == item)
            return true;
      }

      return false;
   }

   /**
    * Get all the employees with a high salery
    * 
    * @param employees list of employees
    * @param salaryLimit the salary limit which make a salary a high salary
    * @return
    */
   public static Set<Employee> getEmployeesWithHighIncome(Map<String, Employee> employees, BigDecimal salaryLimit)
   {
      Set<Employee> results = new HashSet<Employee>();

      for (String username : employees.keySet())
      {
         Employee employee = employees.get(username);
         BigDecimal income = employee.getIncome();
         if (income.compareTo(salaryLimit) > 0)
         {
            results.add(employee);
         }
      }

      return results;
   }

   /**
    * Construct a big string (for testing purposes)
    * 
    * @return a really big string
    */
   public static String getBigConcatenatedString()
   {
      StringBuilder reallyBigString = new StringBuilder("This is just the begining");
      for (int i = 0; i < 10000; i++)
      {
         reallyBigString.append(" Number -> " + i);
         
      }
      return reallyBigString.toString();
      
      

   }

   /**
    * Compare to string objects
    * 
    * @param string1
    * @param string2
    * @return
    */
   public static boolean compareStrings(String string1, String string2)
   {
	   if(null != string1 && null != string2)
		   return string1.equals(string2);
	   else
		   return false;
   }
}
