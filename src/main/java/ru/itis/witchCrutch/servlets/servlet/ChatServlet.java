package ru.itis.witchCrutch.servlets.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.itis.witchCrutch.models.Message;
import ru.itis.witchCrutch.models.User;
import ru.itis.witchCrutch.repositories.MessageRepository;
import ru.itis.witchCrutch.repositories.MessageRepositoryJdbcImpl;
import ru.itis.witchCrutch.repositories.UsersRepository;
import ru.itis.witchCrutch.repositories.UsersRepositoryJdbcImpl;
import ru.itis.witchCrutch.services.MessageService;
import ru.itis.witchCrutch.services.MessageServiceImpl;
import ru.itis.witchCrutch.services.UsersService;
import ru.itis.witchCrutch.services.UsersServiceImpl;
import ru.itis.witchCrutch.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);

        MessageRepository messageRepository = new MessageRepositoryJdbcImpl(dataSource, usersService);
        MessageService messageService = new MessageServiceImpl(messageRepository);

        User user = (User) req.getServletContext().getAttribute("user");

        List<Message> messages = messageService.userMessage(user);
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/chat.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = (DataSource) req.getServletContext().getAttribute("datasource");

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);

        MessageRepository messageRepository = new MessageRepositoryJdbcImpl(dataSource, usersService);
        MessageService messageService = new MessageServiceImpl(messageRepository);

        User user = (User) req.getServletContext().getAttribute("user");

        List<String> parameters = loadFile(req);

        String filename = parameters.get(0);
        String text = parameters.get(1);

        Timestamp date = new Timestamp(System.currentTimeMillis());

        messageService.uploadMessage(Message.builder().message(text).filename(filename).sender(user).receiver(User.builder().id(Constants.ADMIN_ID).build()).build());

        List<Message> messages = messageService.userMessage(user);
        req.setAttribute("messages", messages);

        req.getRequestDispatcher("/chat.ftl").forward(req, resp);
    }

    private List<String> loadFile(HttpServletRequest req) {
        String filename = "";
        String text = "";
        List<String> result = new ArrayList<>();
        if (ServletFileUpload.isMultipartContent(req)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024 * 1024);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(1024 * 1024 * 50);
            upload.setSizeMax(1024 * 1024 * 50);
            String uploadPath = "C:\\Users\\User\\Desktop\\Project\\itis\\third_semester\\semester_work_1\\WitchCrutch\\src\\main\\webapp\\WEB-INF\\uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            List<FileItem> formItems;
            try {
                formItems = upload.parseRequest(req);
                if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {
                        if (!item.isFormField() && item.getSize() > 0) {
                            filename = UUID.randomUUID().toString().substring(0, 8) + ".png";
                            String filePath = uploadPath + File.separator + filename;
                            File storeFile = new File(filePath);
                            item.write(storeFile);
                        } else if (item.getFieldName().equals("text")) {
                            text = item.getString("UTF-8");
                            System.out.println(text);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        result.add(filename);
        result.add(text);
        return result;
    }
}
