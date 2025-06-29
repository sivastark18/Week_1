package MyService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.ExternalApi;
import com.example.MyService;

public class MyServiceTest {
    @Test
    public void testExternalApi() {
        // Step 1: Create a mock
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Step 2: Stub method
        when(mockApi.getData()).thenReturn("Mock Data");

        // Step 3: Inject mock into service
        MyService service = new MyService(mockApi);

        // Step 4: Assert result
        String result = service.fetchData();
        assertEquals("Mock Data", result);
    }
}
