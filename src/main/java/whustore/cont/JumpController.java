package whustore.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import whustore.model.Recommend;
import whustore.service.RecommendService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class JumpController {

    /**
     * 跳转到主页面
     *
     * @return
     */
    @RequestMapping("home")
    public String toHomePage() {
        return "homepage";
    }

    /**
     * 随便逛逛页面跳转
     *
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping("HangingAround")
    public ModelAndView hangAround(HttpServletRequest request, ModelMap modelMap) {
        RecommendService service = new RecommendService();
        Recommend recommend = service.getRecommendation();
        request.getSession().setAttribute("recommend", recommend);
        return new ModelAndView("HangingAroundPage");
    }
}
