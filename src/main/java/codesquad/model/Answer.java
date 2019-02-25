package codesquad.model;

import codesquad.dto.AnswerDTO;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_question"))
    private Question question;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    private User writer;

    @ColumnDefault(value = "false")
    private boolean deleted;

    @Lob
    private String contents;

    private LocalDateTime createDate = LocalDateTime.now();

    public Answer(Long questionId, User writer, String contents) {
        this.writer = writer;
        this.contents = contents;
        this.createDate = LocalDateTime.now();
    }

    public Answer(Question question, User writer, String contents) {
        this.question = question;
        this.writer = writer;
        this.contents = contents;
        this.createDate = LocalDateTime.now();
    }

    public Answer(Question question, User writer, AnswerDTO answerDto) {
        this.question = question;
        this.writer = writer;
        this.contents = answerDto.getContents();
        this.createDate = LocalDateTime.now();
    }

    public String getFormattedCreateDate() {
        if (createDate == null) {
            return "";
        }
        return createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
