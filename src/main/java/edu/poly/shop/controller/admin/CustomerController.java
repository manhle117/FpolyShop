package edu.poly.shop.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Category;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Product;
import edu.poly.shop.model.CategoryDto;
import edu.poly.shop.model.CustomerDto;
import edu.poly.shop.service.CategoryService;
import edu.poly.shop.service.CustomerService;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.StorageService;

@Controller
@RequestMapping("/admin/customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;

	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("customer", new CustomerDto());
		return "admin/customers/addOrEdit";
	}
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("customer") CustomerDto dto,
			BindingResult result) {
		if (result.hasErrors()) {
			
			return new ModelAndView("admin/customers/addOrEdit");
		}
		Customer entity = new Customer();
		dto.setRegisteredDate(new Date());
		
		BeanUtils.copyProperties(dto, entity);
		
		customerService.save(entity);
		
		model.addAttribute("message","Customer is saved");
		return new ModelAndView("forward:/admin/customers/", model);
	}

	@RequestMapping("")
	public String list(ModelMap model) {
		List<Customer> list = customerService.findAll();
		
		model.addAttribute("customers", list);
		
		return "admin/customers/list";
	}

	@GetMapping("search")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {

		List<Customer> list = null;

		if (StringUtils.hasText(name)) {
			list = customerService.findByNameContaining(name);

		} else {
			list = customerService.findAll();
		}
		model.addAttribute("products", list);
		return "admin/products/search";
	}
	@GetMapping("edit/{customerId}")
	public ModelAndView edit(ModelMap model, @PathVariable("customerId") Long customerId) {
		Optional<Customer> opt = customerService.findById(customerId);
		CustomerDto dto = new CustomerDto();

		if (opt.isPresent()) {
			Customer entity = opt.get();

			BeanUtils.copyProperties(entity, dto);	
			dto.setIsEdit(true);
					
			model.addAttribute("customer", dto);
		
			
			return new ModelAndView("admin/customers/addOrEdit", model);

		}
		model.addAttribute("message", "customer is not existed");
		return new ModelAndView("forward:/admin/customers/", model);
	}

	@GetMapping("delete/{customerId}")
	public ModelAndView delete(ModelMap model, @PathVariable("customerId") Long customerId) {
		customerService.deleteById(customerId);
		model.addAttribute("message", "Customer is deleted!");
		return new ModelAndView("forward:/admin/customers/", model);
	}


//

//	@GetMapping("searchpaginated")
//	public String search(ModelMap model,
//			@RequestParam(name = "name", required = false) String name,
//			@RequestParam("page") Optional<Integer>page,
//			@RequestParam("size") Optional<Integer>size
//			) {
//		
//		int currentPage = page.orElse(1);
//		int pageSize = size.orElse(5);
//		
//		Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("name"));
//		Page<Category> resultPage = null;
//		
//		
//		if (StringUtils.hasText(name)) {
//			resultPage = categoryService.findByNameContaining(name,pageable);
//			model.addAttribute("name",name);
//		} else {
//			resultPage = categoryService.findAll(pageable);
//		}
//		
//		int totalPages = resultPage.getTotalPages();
//		if(totalPages >0) {
//			int start = Math.max(1,currentPage-2);
//			int end = Math.min(currentPage+2,totalPages);
//			
//			if (totalPages >5) {
//				if (end == totalPages) start = end -5;
//				else if (start == 1) end = start +5;	
//				
//			}
//			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
//					.boxed()
//					.collect(Collectors.toList());
//			model.addAttribute("pageNumbers",pageNumbers);
//		}
//		
//		model.addAttribute("categoryPage", resultPage);
//		return "admin/products/searchpaginated";
//	}

}
