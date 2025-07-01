package com.ssh.memo_app.controller;


import com.ssh.memo_app.repository.MemoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemoController {

    private final MemoRepository memoRepository;

    public MemoController(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @GetMapping("/")
    public String listMemos(Model model) {
        model.addAttribute("memos", memoRepository.findAll()); //memos 라고 그냥 지어주는 것

        return "memo-list";
    } //기본 페이지

    @GetMapping("/edit/{id}")
    public String editForm(
            @PathVariable int id,
            Model model
    ) {
        model.addAttribute("memo", memoRepository.findById(id)); //id는 PathVariable 로 받아옴
        return "memo-edit";
    }


    @PostMapping("/add")  //Repository- save에 필요한 인자를 여기서 받아옴
    public String addMemo(
            @RequestParam String title,
            @RequestParam String content
    ) {
        memoRepository.save(title, content);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String editMemo(
            @RequestParam int id,
            @RequestParam String title,
            @RequestParam String content
    ) {
        memoRepository.update(id, title, content);

        return "redirect:/";
    }


    @PostMapping("/delete")
    public String deleteMemo(@RequestParam int id) {
        memoRepository.delete(id);
        return "redirect:/";
    }
    //삭제를 할 시에는 고유한 값으로 삭제해야되기때문에 id로 설정하고 삭제해야됨
}
