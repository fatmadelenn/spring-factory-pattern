# Factory Design Pattern with Spring Boot
Factory design pattern is a structure based on creating a class. 
# Implementation
We're going to create a Car interface and concrete classes implementing the Car interface.(Cabrio, Sedan, Hatchback)
A factory class CarFactory is defined as a next step. (This class to get a Car object)

### Step 1
Create an interface.
```
public interface Car {
    String getType();
}
```
### Step 2
Create concrete classes(Cabrio, Sedan, Hatchback) implementing the Car interface.

```@Component
public class Cabrio implements Car{
    @Override
    public String getType() {
        return "Cabrio Car has produced.";
    }
}
```
.
.
.

### Step 3
Create a Factory to generate object of concrete class based. There is a list of cars and a hashmap in this factory class.
In this hashmap put car class and list of cars.

```
@Component
public class CarFactory {
    @Autowired
    private List<Car> cars;
    private static final HashMap<Class, Car> carHashMap = new HashMap<>();
    @PostConstruct
    public void initCarFactory() {
        for (Car car : cars) {
            carHashMap.put(car.getClass(), car);
        }
    }
    public static Car getCar(String name) {
        return carHashMap.get(carHashMap.keySet().stream()
                .filter(carClass -> carClass.getSimpleName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found")));
    }
}
```
### Step 4
When all this is set, we'll create a CarService class that will provide us with an implementation of CarFactory depending on the argument that we supply to the getCar() method:

```
@Service
public class CarService {
    private static final Logger logger = LoggerFactory.getLogger(CarService.class);
    public String getType(String name) {
        Car car = CarFactory.getCar(name);
        logger.info("Result : {}", car.getType());
        return car.getType();
    }
}
```
### Step 5
Publish a rest controller. 

```
@RestController
public class CarController {
    @Autowired
    private CarService carService;
    @GetMapping(value = "api/car/{name}")
    public String getType(@PathVariable String name) {
        return carService.getType(name);
    }
}
```
### Run it
>gradlew build

### Testing
> curl -X GET http://localhost:8080/api/car/{name} 

Example: 
>curl -X GET http://localhost:8080/api/car/cabrio

### Example
 > Input : cabrio
 
 > Output : Cabrio Car has produced.





