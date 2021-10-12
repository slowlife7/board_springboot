package simple.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simple.board.SessionConst;
import simple.board.model.*;
import simple.board.service.PostService;

@Slf4j
@Controller
@RequestMapping("/post")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    String posts(@SessionAttribute(name=SessionConst.MY_SESSION_ID, required = false) User user, @RequestParam(defaultValue = "1") Long pageNum, Model model) {
        PostListWithPageInfo postListWithPageInfo = postService.findPage(pageNum.intValue());
        model.addAttribute("pageInfo", postListWithPageInfo);
        model.addAttribute("user", user);
        return "post/post";
    }

    @GetMapping("/{id}")
    String getPost(@PathVariable Long id, @SessionAttribute(name=SessionConst.MY_SESSION_ID, required = false) User user, Model model) {

        log.info("post id:{}", id);
        PostComment postWithComments = postService.getPostWithComments(id);

        model.addAttribute("article", postWithComments);

        log.info("user:{}",user);

        model.addAttribute("user", user);
        return "post/article";
    }

    @GetMapping("/add")
    String addForm(@SessionAttribute(name=SessionConst.MY_SESSION_ID, required = false) User user, Model model) {
        if(user == null) {
            return "redirect:/member/loginForm";
        }

        model.addAttribute("user", user);
        return "post/addForm";
    }

    @PostMapping("/add")
    String add(@SessionAttribute(name=SessionConst.MY_SESSION_ID, required = false) User user, @ModelAttribute Post post) {

        if(user == null ){
            return "redirect:/member/loginForm";
        }

        log.info("post:{}", post.toString());

        post.setAuthor(user.getId());
        postService.savePost(post);
        return "redirect:/post";
    }

    @PostMapping("/{postid}/comment/add")
    String addComment(@PathVariable Long postid, @SessionAttribute(name=SessionConst.MY_SESSION_ID, required = false) User user, @ModelAttribute Comment comment) {
        if(user == null ){
            return "redirect:/member/loginForm";
        }

        comment.setAuthor(user.getId());
        
        if(postService.saveCommentToPost(postid, comment) == null) {
            return "redirect:/member/loginForm";
        }

        return "redirect:/post/"+postid;
    }

    @GetMapping("/modify/{postid}")
    String modifyArticle(@PathVariable Long postid, @SessionAttribute(name=SessionConst.MY_SESSION_ID, required = false) User user, Model model) {
        if(user == null) {
            return "redirect:/member/loginForm";
        }

        Post post = postService.findPostById(postid);
        if(post == null) {
            return "redirect:/post";
        }

        model.addAttribute("post", post);
        model.addAttribute("user", user);
        return "post/modifyForm";
    }
}
