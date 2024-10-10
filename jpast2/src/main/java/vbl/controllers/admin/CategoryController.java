package vbl.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vbl.entity.Category;
import vbl.services.ICategoryService;
import vbl.services.impl.CategoryService;
import vbl.utils.Constant;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert", "/admin/category/edit", "/admin/category/update", "/admin/category/delete" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public ICategoryService categoryService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("categories")) {
			List<Category> list = categoryService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);

		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Category category = categoryService.findById(id);
			req.setAttribute("cate", category);
			
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				categoryService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
			
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("insert")) {
			Category category = new Category();

			String categoryname = req.getParameter("categoryname");
			String status = req.getParameter("status");
			int statuss = Integer.parseInt(status);
			category.setCategoryname(categoryname);
			category.setStatus(statuss);

			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);

			// Create the upload directory if it doesn't exist
			if (!uploadDir.exists()) {
			    uploadDir.mkdir();
			}

			try {
			    // Get the image part from the request
			    Part part = req.getPart("images");
			    
			    // Check if the part has content (file was uploaded)
			    if (part != null && part.getSize() > 0) {
			        String originalFilename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			        
			        // Extract file extension
			        int index = originalFilename.lastIndexOf(".");
			        if (index > 0) {
			            String ext = originalFilename.substring(index + 1).toLowerCase();
			            
			            // Only allow specific image file extensions
			            if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("gif")) {
			                // Rename file with a timestamp to avoid collisions
			                fname = System.currentTimeMillis() + "." + ext;
			                
			                // Save the file to the upload directory
			                part.write(uploadPath + File.separator + fname);
			                
			                // Save the filename in your category object
			                category.setImages(fname);
			            } else {
			                // Handle invalid file extensions
			                throw new IllegalArgumentException("Invalid file type. Only JPG, JPEG, PNG, or GIF allowed.");
			            }
			        }
			    } else {
			        // Default image if no file is uploaded
			        category.setImages("default-avatar.png");
			    }
			} catch (Exception e) {
			    e.printStackTrace();
			    // Handle exceptions like logging, setting error messages, etc.
			}

			
			categoryService.insert(category);
			System.out.println(category.toString());
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("update")) {
		    try {
		        // Lấy và chuyển đổi categoryid
		        int categoryid = Integer.parseInt(req.getParameter("categoryId"));

		        // Lấy các thông tin khác từ form
		        String categoryname = req.getParameter("categoryname");

		        // Kiểm tra và chuyển đổi status thành int, nếu không hợp lệ trả về 0
		        String status = req.getParameter("status");
		        int statuss;
		        try {
		            statuss = Integer.parseInt(status);
		        } catch (NumberFormatException e) {
		            statuss = 0; // Giá trị mặc định hoặc có thể đưa ra thông báo lỗi
		        }

		        

		        // Tạo đối tượng category
		        Category category = new Category();
		        category.setCategoryId(categoryid);
		        category.setCategoryname(categoryname);
		        category.setStatus(statuss);
		        // lưu hình ảnh cũ
		        Category cateold = categoryService.findById(categoryid);
		        String fileold = cateold.getImages();
		        // Xử lý images
		        String fname = "";
		        String uploadPath = Constant.UPLOAD_DIRECTORY;
		        File uploadDir = new File(uploadPath);

		        // Create the upload directory if it doesn't exist
		        if (!uploadDir.exists()) {
		            uploadDir.mkdir();
		        }

		        try {
		            // Get the image part from the request
		            Part part = req.getPart("images");
		            
		            // Check if the part has content (file was uploaded)
		            if (part != null && part.getSize() > 0) {
		                String originalFilename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
		                
		                // Extract file extension
		                int index = originalFilename.lastIndexOf(".");
		                if (index > 0) {
		                    String ext = originalFilename.substring(index + 1).toLowerCase();
		                    
		                    // Only allow specific image file extensions
		                    if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("gif")) {
		                        // Rename file with a timestamp to avoid collisions
		                        fname = System.currentTimeMillis() + "." + ext;
		                        
		                        // Save the file to the upload directory
		                        part.write(uploadPath + File.separator + fname);
		                        
		                        // Save the filename in your category object
		                        category.setImages(fname);
		                    } else {
		                        // Handle invalid file extensions
		                        throw new IllegalArgumentException("Invalid file type. Only JPG, JPEG, PNG, or GIF allowed.");
		                    }
		                }
		            } else {
		                // Default image if no file is uploaded
		                category.setImages(fileold);
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		            // Handle exceptions like logging, setting error messages, etc.
		        }

		        
		        // Cập nhật category vào cơ sở dữ liệu
		        categoryService.update(category);

		        // Điều hướng lại trang sau khi cập nhật thành công
		        resp.sendRedirect(req.getContextPath() + "/admin/categories");
		    } catch (Exception e) {
		        e.printStackTrace();
		        // Xử lý lỗi, có thể chuyển hướng tới trang lỗi hoặc hiển thị thông báo
		        resp.sendRedirect(req.getContextPath() + "/admin/error");
		    }
		} else if (url.contains("delete")) {
			
		}

	}
}
