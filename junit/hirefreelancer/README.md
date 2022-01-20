# JUNIT & Mockito
 -@InjectMocks 
 -@Mock
  -@Spy
  -@Captor
Instead of set up code we use annotations!! 

```
@BeforeEach
    void setup() {
        this.depositServiceMock = spy(DepositService.class);
        this.freelancerServiceMock = spy(FreelancerService.class);
        this.hiringDAOMock = spy(HiringDAO.class);
        this.notificationMock = mock(Notification.class);
        this.hiringService = new HiringService(depositServiceMock, freelancerServiceMock, hiringDAOMock, notificationMock);
        this.doubleCaptor=ArgumentCaptor.forClass(Double.class);
        this.hiringRequestArgumentCaptor=ArgumentCaptor.forClass(HiringRequest.class);
    }


@ExtendWith(MockitoExtension.class)
class HiringServiceTest {
    @InjectMocks
    private HiringService hiringService;
    @Spy
    private DepositService depositServiceMock;
    @Spy
    private FreelancerService freelancerServiceMock;
    @Spy
    private HiringDAO hiringDAOMock;
    @Mock
    private Notification notificationMock;
    @Captor
    private ArgumentCaptor<Double> doubleCaptor;
    @Captor
    private ArgumentCaptor<HiringRequest> hiringRequestArgumentCaptor;

...

```

HiringServiceTest should be independent from springboot tests. So normal tests should be done.

We can inject   MockBean to controller to test rest services
```
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HireFreeLancerControllerTest {
   @MockBean
   private HiringService hiringService;
   ...
```


Mocks return default values so using spy gives us more information about methods.


