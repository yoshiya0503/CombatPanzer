package AbstractPanzers;

abstract public class AbstractPanzerFactory {	
	public static AbstractPanzerFactory createFactory(String classname){
		AbstractPanzerFactory factory = null;
		try{
			factory = (AbstractPanzerFactory)Class.forName(classname).newInstance();
		}catch(ClassNotFoundException e){
			System.out.println(classname + "Ç™ë∂ç›ÇµÇ»Ç¢");
		}catch(Exception e){
			e.printStackTrace();
		}
		return factory;
	}
	public abstract AbstractMoveRange createMove();
	public abstract AbstractAttackRange createRange();
}
