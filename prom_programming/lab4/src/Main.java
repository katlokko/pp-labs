import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.*;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class gradeBook {
    class Session{
        class Subject{
            private String name;
            private int mark;

            Subject(String subjName, int subjMark){
                name = subjName;
                mark = subjMark;
            }

            @Override
            public String toString(){
                return name + ": " + mark;
            }
        }

        Vector<Subject> subjects;

        Session(){
            subjects = new Vector<>();
        }

        void addSubj(String subj, int mark){
            subjects.add(new Subject(subj, mark));
        }

        Session(String subj, int mark){
            subjects = new Vector<>();
            addSubj(subj, mark);
        }

        @Override
        @JsonValue
        public String toString(){
            return subjects.toString();
        }
    }

    public int num;
    private String name;
    private String secondName;
    private String surname;
    private int year;
    private int group;
    @JsonIgnore
    private HashMap<Integer, Session> sessions;

    gradeBook(){
        sessions = new HashMap<>();
    }

    gradeBook(int Num, String studentSecondName, String studentName, String studentSurname, int studentYear, int studentGroup){
        num = Num;
        name = studentName;
        secondName = studentSecondName;
        surname = studentSurname;
        year = studentYear;
        group = studentGroup;
        sessions = new HashMap<>();
    }

    int getNum(){
        return num;
    }

    void addSubj(int session, String subject, int mark){
        if(sessions.containsKey(session)){
            sessions.get(session).addSubj(subject, mark);
        }
        else{
            sessions.put(session, new Session(subject, mark));
        }
    }

    @Override
    public String toString(){
        return num + " " + secondName + " " + name + " " + surname + " "
                + year + " " + group + " " + sessions;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<gradeBook> students = new ArrayList<>();
        Scanner cin = new Scanner(System.in);
        System.out.print("Write type of students file: ");
        String format = cin.nextLine();
        if(format.equals("txt")){
            File studFile = new File("students.txt");
            Scanner in = new Scanner(studFile);
            while(in.hasNext()){
                students.add(new gradeBook(in.nextInt(), in.next(), in.next(), in.next(),
                        in.nextInt(), in.nextInt()));
            }
        }
        else if(format.equals("json")){
            File studFile = new File("students.json");
            students = new ObjectMapper().readValue(studFile, new TypeReference<ArrayList<gradeBook>>(){});
        }

        for(gradeBook gb: students){
            System.out.println(gb);
        }

        String file, subj;
        Scanner examIn;
        int N, session;
        while(true) {
            System.out.print("Type a name of exam file (or N if you wanna leave program): ");
            file = cin.nextLine();
            if(file.equals("N")) break;
            File examFile = new File(file);
            examIn = new Scanner(examFile);

            subj = examIn.nextLine();
            session = examIn.nextInt();
            while(examIn.hasNext()){
                N = examIn.nextInt();
                for(gradeBook gb: students){
                    if(gb.getNum() == N){
                        gb.addSubj(session, subj, examIn.nextInt());
                        break;
                    }
                }
            }
        }

        for(gradeBook gb: students){
            System.out.println(gb);
        }

        File jsonFile = new File("students.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT)
                .writeValue(jsonFile, students);
    }
}