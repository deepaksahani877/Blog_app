package com.blog.entities;



import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "post_title",nullable = false)
    private String title;

    @Column(name = "post_url",nullable = false,unique = true)
    private String url;

    @Lob
    @Column(name = "post_content",nullable = false,length =Integer.MAX_VALUE)
    private String content;

    @Column(name = "short_description" ,nullable = false)
    private String shortDescription;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

}
