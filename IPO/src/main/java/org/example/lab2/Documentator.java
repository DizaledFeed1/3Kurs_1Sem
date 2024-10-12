package org.example.lab2;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


import java.io.FileNotFoundException;
import java.lang.reflect.*;
import java.util.HashSet;
import java.util.Set;

public class Documentator {
    private static Set<Class<?>> classes = new HashSet<Class<?>>();

    public static void createDocumentacion(Class MyClass, Document document) {
        if (classes.contains(MyClass)) {
            return;
        }
            classes.add(MyClass);

            //Имя класса
            document.add(new Paragraph(MyClass.getSimpleName()));
//            System.out.println("Name: " + MyClass.getSimpleName());
            // Поля
//            System.out.println("Field: ");
            document.add(new Paragraph("Field:"));
            for (Field f : MyClass.getDeclaredFields()) {
                document.add(new Paragraph( f + " of type " + f.getType().getSimpleName()));
//                System.out.println(f + " of type " + f.getType());

                if (f.getType().isArray()) {
                    Class<?> componentType = f.getType().getComponentType();
//                    System.out.println(componentType);
                    if (!f.getType().isPrimitive() &&
                            !f.getType().getName().startsWith("java.") &&
                            !classes.contains(f.getType())) {
                        document.add(new Paragraph("{"));
//                        System.out.println(componentType);
                        createDocumentacion(componentType,document);
                        document.add(new Paragraph("}"));
//                    System.out.println("}");
                    }
                    continue;
                }

                if (!f.getType().isPrimitive() &&
                        !f.getType().getName().startsWith("java.") &&
                        !classes.contains(f.getType())) {
                    document.add(new Paragraph("{"));
                    createDocumentacion(f.getType(),document);
                    document.add(new Paragraph("}"));
//                    System.out.println("}");
                }
            }
            //Конструкторы
//            System.out.println("Constructors:");
            document.add(new Paragraph("Constructors"));
            for (Constructor c : MyClass.getDeclaredConstructors()) {
                document.add(new Paragraph(c.toString()));
//                System.out.println(c.getName());
            }
            //Методы
//            System.out.println("Metods:");
            document.add(new Paragraph("Metods:"));
            for (Method m : MyClass.getDeclaredMethods()) {
                document.add(new Paragraph(m.toString()));
//                System.out.println(m);
            }
    }
}
