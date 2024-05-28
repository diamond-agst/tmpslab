import java.util.ArrayList;
import java.util.List;

// Observer Pattern
interface Observer {
    void update(String status);
}

class Subject {
    private List<Observer> observers = new ArrayList<>();
    private String status;

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(status);
        }
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    public String getStatus() {
        return status;
    }
}

class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String status) {
        System.out.println("Customer " + name + ": Order status updated to: " + status);
    }
}

// Strategy Pattern
interface Strategy {
    double calculatePrice(double basePrice);
}

class NoDiscountStrategy implements Strategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice;
    }
}

class SeasonalDiscountStrategy implements Strategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 0.9;
    }
}

class MemberDiscountStrategy implements Strategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 0.8;
    }
}

class Pizza {
    private double basePrice;
    private Strategy pricingStrategy;

    public Pizza(double basePrice) {
        this.basePrice = basePrice;
    }

    public void setPricingStrategy(Strategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double getPrice() {
        return pricingStrategy.calculatePrice(basePrice);
    }
}

// Command Pattern
interface Command {
    void execute();
}

class PizzaOrder {
    public void prepare() {
        System.out.println("Preparing the pizza.");
    }

    public void bake() {
        System.out.println("Baking the pizza.");
    }

    public void pack() {
        System.out.println("Packing the pizza.");
    }
}

class PrepareCommand implements Command {
    private PizzaOrder pizzaOrder;

    public PrepareCommand(PizzaOrder pizzaOrder) {
        this.pizzaOrder = pizzaOrder;
    }

    @Override
    public void execute() {
        pizzaOrder.prepare();
    }
}

class BakeCommand implements Command {
    private PizzaOrder pizzaOrder;

    public BakeCommand(PizzaOrder pizzaOrder) {
        this.pizzaOrder = pizzaOrder;
    }

    @Override
    public void execute() {
        pizzaOrder.bake();
    }
}

class PackCommand implements Command {
    private PizzaOrder pizzaOrder;

    public PackCommand(PizzaOrder pizzaOrder) {
        this.pizzaOrder = pizzaOrder;
    }

    @Override
    public void execute() {
        pizzaOrder.pack();
    }
}

class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}

// Main Class to Demonstrate Patterns
public class Main {
    public static void main(String[] args) {
        // Observer pattern example
        Subject order = new Subject();

        Observer customer1 = new ConcreteObserver("John");
        Observer customer2 = new ConcreteObserver("Alice");

        order.attach(customer1);
        order.attach(customer2);

        order.setStatus("Preparing");
        order.setStatus("Ready for pickup");

        // Strategy pattern example
        Pizza pizza = new Pizza(10.0);

        pizza.setPricingStrategy(new NoDiscountStrategy());
        System.out.println("Price with no discount: " + pizza.getPrice());

        pizza.setPricingStrategy(new SeasonalDiscountStrategy());
        System.out.println("Price with seasonal discount: " + pizza.getPrice());

        pizza.setPricingStrategy(new MemberDiscountStrategy());
        System.out.println("Price with member discount: " + pizza.getPrice());

        // Command pattern example
        PizzaOrder pizzaOrder = new PizzaOrder();

        Command prepareCommand = new PrepareCommand(pizzaOrder);
        Command bakeCommand = new BakeCommand(pizzaOrder);
        Command packCommand = new PackCommand(pizzaOrder);

        Invoker invoker = new Invoker();

        invoker.setCommand(prepareCommand);
        invoker.executeCommand();

        invoker.setCommand(bakeCommand);
        invoker.executeCommand();

        invoker.setCommand(packCommand);
        invoker.executeCommand();
    }
}
