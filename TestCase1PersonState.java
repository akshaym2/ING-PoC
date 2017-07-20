package nl.aegon.assessment.cases;

import java.util.Date;

import org.junit.Assert;

import junit.framework.TestCase;
import nl.aegon.assessment.cases.helpers.MyDate;
import nl.aegon.assessment.cases.model.Employee;

/**
 * A test-case about the state of object and immutable versus mutable objects.
 * 
 * @version $Id:$
 * @author wingerma (c) 8 apr. 2014, Sogeti B.V.
 */
public class TestCase1PersonState extends TestCase
{
   /**
    * What happens here. Change the model in such a way that this unit test succeeds
    */
   public void testDateGet()
   {
      Date now = new Date();
      Date originalInput = (Date) now.clone();
      // construct a person who is born now
      Employee employee = getTestEmployee(now);

      // Get the birthday and change the object to which it refers
      Date response = employee.getBirthday();
      response.setTime(response.getTime() - 1000000);

      // does the getter still return the original input? 
      Assert.assertEquals(originalInput, employee.getBirthday());
   }

   /**
    * What happens here. Change the model in such a way that this unit test succeeds
    */
   public void testDateSet()
   {
      Date input = new Date();
      Date originalInput = (Date) input.clone();
      Employee employee = getTestEmployee();

      // Set the birthday and change the input which was supplied
      employee.setBirthday(input);
      input.setTime(input.getTime() - 1000000);

      // does the getter still return the original input? 
      Assert.assertEquals(originalInput, employee.getBirthday());
   }

   /**
    * The method in the model looks okay, but what is wrong there?
    */
   public void testUpperCaseLastname()
   {
      Employee p = getTestEmployee();
      p.setLastname("test");

      Assert.assertEquals("TEST", p.getLastnameUppercase());
   }

   /**
    * Make sure that this one still succeeds as well
    */
   public void testDateNull()
   {
      Employee employee = getTestEmployee();
      employee.setBirthday(null);
      assertEquals(null, employee.getBirthday());

      employee.setLastname(null);
      Assert.assertEquals(null, employee.getLastnameUppercase());
   }

   /**
    * Make sure that this one still succeeds as well
    */
   public void testDateCloneNotTheBestSolution()
   {
      Date input = new MyDate();
      Date originalInput = new Date(input.getTime());
      // construct a person who is born now
      Employee employee = getTestEmployee(input);

      // Get the birthday and change the object to which it refers
      Date response = employee.getBirthday();
      response.setTime(response.getTime() - 1000000);

      // does the getter still return the original input? 
      Assert.assertEquals(originalInput, employee.getBirthday());

      // Set the birthday and change the input which was supplied
      employee.setBirthday(input);
      input.setTime(input.getTime() - 1000000);

      // does the getter still return the original input? 
      Assert.assertEquals(originalInput, employee.getBirthday());
   }

   /**
    * Get a test employee object
    * 
    * @param birthday
    * @return
    */
   private Employee getTestEmployee(Date birthday)
   {
      return new Employee(1, "Test", "Gebruiker", birthday);
   }

   /**
    * Get a test user without a birtday
    * 
    * @return
    */
   private Employee getTestEmployee()
   {
      return getTestEmployee(null);
   }
}