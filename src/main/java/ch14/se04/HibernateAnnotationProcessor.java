package ch14.se04;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
// 指定可以处理这三个Annotation
@SupportedAnnotationTypes({"Persistent", "Id", "Property"})
public class HibernateAnnotationProcessor extends AbstractProcessor {

	// 循环处理每个需要处理的程序对象
	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		// 定义一个文件输出流，用于生成额外的文件
		PrintStream ps = null;
		try {
			// 遍历每个被@Persistent修饰的class文件
			for(Element t : roundEnv.getElementsAnnotatedWith(Persistent.class)) {
				// 获取正在处理的类名
				Name className = t.getSimpleName();
				// 获取类定义前的@Persistent
				Persistent per = t.getAnnotation(Persistent.class);
				ps = new PrintStream(new FileOutputStream(className+".hbm.xml"));
				// 执行输出
				ps.println("<?xml version=\"1.0\"?>");
				ps.println("<!DOCTYPE hibernate-mapping PUBLIC");
				ps.println("	\"-//Hibernate/Hibernate "
					+ "Mapping DTD 3.0//EN\"");
				ps.println("	\"http://www.hibernate.org/dtd/"
					+ "hibernate-mapping-3.0.dtd\">");
				ps.println("<hibernate-mapping>");
				ps.println("	<class name=\"" + t);
				ps.println("\" table=\"" + per.table() + "\">");
				for(Element f : t.getEnclosedElements()) {
					// 只处理成员变量上的Annotation
					if(f.getKind() == ElementKind.FIELD) {
						Id id = f.getAnnotation(Id.class);
						// 当@Id存在时，输出
						if(id != null) {
							ps.println("		<id name=\""
									+ f.getSimpleName()
									+ "\" column=\"" + id.column()
									+ "\" type=\"" + id.type()
									+ "\">");
							ps.println("		<generator class=\""
									+ id.generator() + "\"/>");
							ps.println("		</id>");
						}
						// 获取成员变量定义前的@Property Annotation
						Property p = f.getAnnotation(Property.class);  // ③
						// 当@Property Annotation存在时输出<property.../>元素
						if (p != null) {
							ps.println("		<property name=\""
								+ f.getSimpleName()
								+ "\" column=\"" + p.column()
								+ "\" type=\"" + p.type()
								+ "\"/>");
						}
					}
				}
				ps.println("	</class>");
				ps.println("</hibernate-mapping>");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return true;
	}

}
