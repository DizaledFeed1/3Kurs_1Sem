package org.example.lab2;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import test.Course;
import test.Student;
import test.Teacher;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    TextField text;
    @FXML
    Button button;
    @FXML
    Label welcomeText;

    Document document;
    Student student;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Teacher teacher = new Teacher("Dr. Smith", "Math");
        Course Algebra = new Course("Algebra", teacher);
        Course Math = new Course("Math", teacher);
        Course[] course = {Algebra, Math};
        student = new Student("Alice", 20, course);
        PdfWriter writer = null;
        try {
            writer = new PdfWriter("output.pdf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PdfDocument pdf = new PdfDocument(writer);
        document = new Document(pdf);
//        Documentator.createDocumentacion(student.getClass(),document);
//        document.close();
    }


    @FXML
    protected void documentation() {
        try {
            Documentator.createDocumentacion(Class.forName("test." + text.getText()), document);
            welcomeText.setText("Готово!");
            document.close();
        } catch (ClassNotFoundException e) {
            text.clear();
            text.setText("Невреное имя класса");
        }
    }
}