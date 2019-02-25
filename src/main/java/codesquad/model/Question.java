package codesquad.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    @OneToMany(mappedBy = "question")
    @OrderBy("id ASC")
    private List<Answer> answers;

    private LocalDateTime createDate = LocalDateTime.now();

    @Column()
    @ColumnDefault(value = "false")
    private boolean deleted;


    @Column(nullable = false, length = 40)
    private String title;

    @Lob
    private String contents;

    public String getFormattedCreateDate() {
        if (createDate == null) {
            return "";
        }
        return createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public void update(Question updateQuestion, User writer) {
        this.setWriter(writer);
        this.setTitle(updateQuestion.title);
        this.setContents(updateQuestion.contents);
    }

    public boolean isEqualWriter(User user) {
        if (this.getWriter().getName().equals(user.getName())) {
            return true;
        } else
            return false;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }


    public boolean isAllSameUser() {
        if(answers.stream()
            .allMatch(answer -> answer.getWriter()
            .isSameUser(writer))){
            return true;
        }
        else
            return false;
    }

    public void setDeletedAnswer() {
        getAnswers().stream()
                .forEach(answer -> answer.setDeleted(true));
        setDeleted(true);
    }
}
