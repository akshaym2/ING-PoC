
package nl.aegon.assessment.cases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Assert;

import junit.framework.TestCase;
import nl.aegon.assessment.cases.model.Department;
import nl.aegon.assessment.cases.model.Employee;

public class TestCase2Serialization extends TestCase
{

   /**
    * Object data should be retained. Make the necessary changes so that Object data is not lost.
    */
   public void testCheckForCorrectnessOfObjectData() throws IOException, ClassNotFoundException
   {
      Department d = new Department("CS");
      Employee e1 = new Employee(1, "Jack", "Sparrow", null);
      e1.setDepartment(d);
      Employee e2 = writeAndReadObject(e1);

      // What could be done for this test-case to succeed ?????
      Assert.assertNotNull(e2.getDepartment());
   }

   private Employee writeAndReadObject(Employee employee) throws IOException, ClassNotFoundException
   {

      final String FILE = "test.ser";

      FileOutputStream fs = new FileOutputStream(FILE);
      ObjectOutputStream os = new ObjectOutputStream(fs);
      os.writeObject(employee);
      os.close();
      fs.close();

      FileInputStream fis = new FileInputStream(FILE);
      ObjectInputStream ois = new ObjectInputStream(fis);
      Employee emp = (Employee) ois.readObject();
      ois.close();
      fis.close();
      return emp;
   }
}
