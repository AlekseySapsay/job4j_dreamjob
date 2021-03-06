package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.Store;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * https://job4j.ru/profile/exercise/82/task-view/443
 * <p>
 * изучение работы с сервлетами
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 22.01.2022
 */

public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("posts", Store.instOf().findAllPosts());
        req.getRequestDispatcher("posts.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Store.instOf().save(
                new Post(Integer.parseInt(
                        req.getParameter("id")),
                        req.getParameter("name"),
                        req.getParameter("description")
                ));
        resp.sendRedirect(req.getContextPath() + "/posts.do");
    }
}
