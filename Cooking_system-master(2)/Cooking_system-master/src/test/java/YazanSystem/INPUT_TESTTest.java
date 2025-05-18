package YazanSystem;

import org.example.INPUT_TEST;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class INPUT_TESTTest {
    private INPUT_TEST inputTest;

    @BeforeEach
    public void setUp() {
        inputTest = new INPUT_TEST();
    }

    @Test
    public void testCustomerLoginAndFetchOrderHistory() {
        List<String> before = inputTest.fetchOrderHistory();
        assertTrue(before == null || before.isEmpty());

        inputTest.loginCustomer();

        List<String> after = inputTest.fetchOrderHistory();
        assertNotNull(after);
        assertTrue(after.contains("Shawerma"));
        assertTrue(after.contains("Pizza"));
    }

    @Test
    public void testAdminLoginAndRequestCustomerOrderData() {
        boolean b1 = inputTest.requestCustomerOrderData();
        assertFalse(b1);

        inputTest.loginAdmin();

        boolean b2 = inputTest.requestCustomerOrderData();
        assertTrue(b2);
    }

    @Test
    public void testAccessStoredOrderHistory() {
        List<String> a1 = INPUT_TEST.accessStoredOrderHistory();
        assertTrue(a1 == null || a1.isEmpty());

        inputTest.loginCustomer();
        inputTest.fetchOrderHistory();

        List<String> a2 = INPUT_TEST.accessStoredOrderHistory();
        assertNotNull(a2);
        assertEquals(2, a2.size());
    }

    @Test
    public void testSuggestPersonalizedMealPlan() {
        String plan = inputTest.suggestPersonalizedMealPlan();
        assertNotNull(plan);
        assertFalse(plan.isEmpty());
    }

    @Test
    public void testAnalyzeTrendsAndGenerateReports() {
        String report = inputTest.analyzeTrendsAndGenerateReports();
        assertNotNull(report);
        assertTrue(report.contains("Report"));
    }

    @Test
    public void testNavigateToCustomerProfile() {
        boolean b1 = inputTest.navigateToCustomerProfile();
        assertFalse(b1);

        inputTest.loginChef();

        boolean b2 = inputTest.navigateToCustomerProfile();
        assertTrue(b2);
    }

    @Test
    public void testSelectMealToReorder() {
        String m1 = inputTest.selectMealToReorder();
        assertTrue(m1 == null || m1.isEmpty());

        inputTest.loginCustomer();
        inputTest.fetchOrderHistory();

        String m2 = inputTest.selectMealToReorder();
        assertNotNull(m2);
        assertEquals("Shawerma", m2);
    }

    @Test
    public void testNavigateToPageAsCustomer() {
        boolean b1 = inputTest.navigateToPage("Order History");
        assertFalse(b1);

        inputTest.loginCustomer();

        boolean b2 = inputTest.navigateToPage("Order History");
        assertTrue(b2);
    }

    @Test
    public void testNavigateToPageAsAdmin() {
        boolean b1 = inputTest.navigateToPage("Admin Dashboard");
        assertFalse(b1);

        inputTest.loginAdmin();

        boolean b2 = inputTest.navigateToPage("Admin Dashboard");
        assertTrue(b2);
    }

    @Test
    public void testNavigateToPageAsChef() {
        boolean b1 = inputTest.navigateToPage("Chef Dashboard");
        assertFalse(b1);

        inputTest.loginChef();

        boolean b2 = inputTest.navigateToPage("Chef Dashboard");
        assertTrue(b2);
    }
}
