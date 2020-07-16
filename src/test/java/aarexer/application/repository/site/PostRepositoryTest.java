package aarexer.application.repository.site;

import aarexer.application.ApplicationTest;
import aarexer.application.model.site.Comment;
import aarexer.application.model.site.Post;
import aarexer.application.model.site.Tag;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PostRepositoryTest extends ApplicationTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TagRepository tagRepository;

    @Before
    public void beforeEach() {
        postRepository.deleteAll();
        commentRepository.deleteAll();
        tagRepository.deleteAll();
    }

    @Test
    public void savePostWithoutCommentsAndTags() {
        Assert.assertEquals(0, postRepository.findAll().size());
        Assert.assertEquals(0, commentRepository.findAll().size());
        Assert.assertEquals(0, tagRepository.findAll().size());

        Post post = new Post("Title", "Description", "Content");
        postRepository.save(post);

        Assert.assertEquals(1, postRepository.findAll().size());
        Assert.assertEquals(0, commentRepository.findAll().size());
        Assert.assertEquals(0, tagRepository.findAll().size());
    }

    @Test
    public void savePostWithSingleCommentAndWithoutTags() {
        Assert.assertEquals(0, postRepository.findAll().size());
        Assert.assertEquals(0, commentRepository.findAll().size());
        Assert.assertEquals(0, tagRepository.findAll().size());

        Post post = new Post("Title", "Description", "Content");
        Comment comment = new Comment("Content of Comment");
        post.getComments().add(comment);

        postRepository.save(post);

        Assert.assertEquals(1, postRepository.findAll().size());
        Assert.assertEquals(1, commentRepository.findAll().size());
        Assert.assertEquals(0, tagRepository.findAll().size());
    }

    @Test
    public void savePostWithCommentsAndWithoutTags() {
        Assert.assertEquals(0, postRepository.findAll().size());
        Assert.assertEquals(0, commentRepository.findAll().size());
        Assert.assertEquals(0, tagRepository.findAll().size());

        Post post = new Post("Title", "Description", "Content");
        Comment comment1 = new Comment("Content of Comment1");
        Comment comment2 = new Comment("Content of Comment2");
        Comment comment3 = new Comment("Content of Comment3");

        post.getComments().add(comment1);
        post.getComments().add(comment2);
        post.getComments().add(comment3);

        postRepository.save(post);

        Assert.assertEquals(1, postRepository.findAll().size());
        Assert.assertEquals(3, commentRepository.findAll().size());
        Assert.assertEquals(0, tagRepository.findAll().size());

        Optional<Post> persist = postRepository.getById(1L);
        Assert.assertTrue(persist.isPresent());

        Post savedPost = persist.get();

        Assert.assertEquals(3, savedPost.getComments().size());
        Assert.assertEquals(Sets.newHashSet(comment1, comment2, comment3), savedPost.getComments());
    }

    @Test
    public void savePostWithoutCommentsAndWithTags() {
        Assert.assertEquals(0, postRepository.findAll().size());
        Assert.assertEquals(0, commentRepository.findAll().size());
        Assert.assertEquals(0, tagRepository.findAll().size());

        Post post = new Post("Title", "Description", "Content");
        Tag tag1 = new Tag("Tag1");
        Tag tag2 = new Tag("Tag2");
        Tag tag3 = new Tag("Tag3");

        post.getTags().add(tag1);
        post.getTags().add(tag2);
        post.getTags().add(tag3);

        postRepository.save(post);

        Assert.assertEquals(1, postRepository.findAll().size());
        Assert.assertEquals(0, commentRepository.findAll().size());
        Assert.assertEquals(3, tagRepository.findAll().size());

        Optional<Post> persist = postRepository.getById(1L);
        Assert.assertTrue(persist.isPresent());

        Post savedPost = persist.get();

        Assert.assertEquals(3, savedPost.getTags().size());
        Assert.assertEquals(Sets.newHashSet(tag1, tag2, tag3), savedPost.getTags());
    }

    @Test
    public void savePostWithCommentsAndWithTags() {
        Assert.assertEquals(0, postRepository.findAll().size());
        Assert.assertEquals(0, commentRepository.findAll().size());
        Assert.assertEquals(0, tagRepository.findAll().size());

        Post post = new Post("Title", "Description", "Content");

        // Tags for post
        Tag tag1 = new Tag("Tag1");
        Tag tag2 = new Tag("Tag2");
        Tag tag3 = new Tag("Tag3");

        post.getTags().add(tag1);
        post.getTags().add(tag2);
        post.getTags().add(tag3);

        // Comments for post
        Comment comment1 = new Comment("Content of Comment1");
        Comment comment2 = new Comment("Content of Comment2");
        Comment comment3 = new Comment("Content of Comment3");

        post.getComments().add(comment1);
        post.getComments().add(comment2);
        post.getComments().add(comment3);

        postRepository.save(post);

        Assert.assertEquals(1, postRepository.findAll().size());
        Assert.assertEquals(3, commentRepository.findAll().size());
        Assert.assertEquals(3, tagRepository.findAll().size());

        Optional<Post> persist = postRepository.getById(1L);
        Assert.assertTrue(persist.isPresent());

        Post savedPost = persist.get();

        Assert.assertEquals(3, savedPost.getTags().size());
        Assert.assertEquals(Sets.newHashSet(tag1, tag2, tag3), savedPost.getTags());

        Assert.assertEquals(3, savedPost.getComments().size());
        Assert.assertEquals(Sets.newHashSet(comment1, comment2, comment3), savedPost.getComments());
    }

    @Test
    public void savePostWithoutMetadataButAddItAfter() {
        Assert.assertEquals(0, postRepository.findAll().size());
        Assert.assertEquals(0, commentRepository.findAll().size());
        Assert.assertEquals(0, tagRepository.findAll().size());

        Post post = new Post("Title", "Description", "Content");
        Post saved = postRepository.save(post);

        Assert.assertEquals(1, postRepository.findAll().size());
        Assert.assertEquals(0, commentRepository.findAll().size());
        Assert.assertEquals(0, tagRepository.findAll().size());

        // Comments for post
        Comment comment1 = new Comment("Content of Comment1");
        Comment comment2 = new Comment("Content of Comment2");
        Comment comment3 = new Comment("Content of Comment3");

        saved.getComments().add(comment1);
        saved.getComments().add(comment2);
        saved.getComments().add(comment3);

        postRepository.save(saved);

        Optional<Post> persist = postRepository.getById(1L);
        Assert.assertTrue(persist.isPresent());

        Post savedPost = persist.get();

        Assert.assertEquals(3, savedPost.getComments().size());
        Assert.assertEquals(Sets.newHashSet(comment1, comment2, comment3), savedPost.getComments());
    }

    @Test
    public void deletePostWithCommentsAndTags() {
        Assert.assertEquals(0, postRepository.findAll().size());
        Assert.assertEquals(0, commentRepository.findAll().size());
        Assert.assertEquals(0, tagRepository.findAll().size());

        Post post = new Post("Title", "Description", "Content");

        // Tags for post
        Tag tag1 = new Tag("Tag1");
        Tag tag2 = new Tag("Tag2");
        Tag tag3 = new Tag("Tag3");

        post.getTags().add(tag1);
        post.getTags().add(tag2);
        post.getTags().add(tag3);

        // Comments for post
        Comment comment1 = new Comment("Content of Comment1");
        Comment comment2 = new Comment("Content of Comment2");
        Comment comment3 = new Comment("Content of Comment3");

        post.getComments().add(comment1);
        post.getComments().add(comment2);
        post.getComments().add(comment3);

        postRepository.save(post);

        Assert.assertEquals(1, postRepository.findAll().size());
        Assert.assertEquals(3, commentRepository.findAll().size());
        Assert.assertEquals(3, tagRepository.findAll().size());

        Optional<Post> persist = postRepository.getById(1L);
        Assert.assertTrue(persist.isPresent());

        Post savedPost = persist.get();

        Assert.assertEquals(3, savedPost.getTags().size());
        Assert.assertEquals(Sets.newHashSet(tag1, tag2, tag3), savedPost.getTags());

        Assert.assertEquals(3, savedPost.getComments().size());
        Assert.assertEquals(Sets.newHashSet(comment1, comment2, comment3), savedPost.getComments());

        postRepository.removeById(1L);

        Assert.assertEquals(0, postRepository.findAll().size());
        Assert.assertEquals(0, commentRepository.findAll().size());
        Assert.assertFalse(tagRepository.findAll().isEmpty());
    }
}
