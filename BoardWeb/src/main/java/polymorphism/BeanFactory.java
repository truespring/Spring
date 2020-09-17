package polymorphism;

public class BeanFactory {
	public Object getBean(String beanName) {
		switch(beanName) {
		case "Samsung":
			return new SamsungTV();
		case "Lg":
			return new LgTV();
		}
		return null;
	}
}
