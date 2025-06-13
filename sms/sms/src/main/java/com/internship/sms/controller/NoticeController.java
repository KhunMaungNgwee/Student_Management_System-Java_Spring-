package com.internship.sms.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.internship.sms.common.ActiveStatus;
import com.internship.sms.common.Response;
import com.internship.sms.common.Util;
import com.internship.sms.entity.Notice;
import com.internship.sms.service.NoticeService;

@RestController
@RequestMapping("/notice/")
@CrossOrigin(origins = "*")
public class NoticeController {

	@Value("${notice.file.path.realPath}")
	private String noticeRealPath;

	@Value("${notice.file.path.relativePath}")
	private String noticeRelativePath;

	@Value("${notice.file.path.defaultPath}")
	private String defaultPhoto;

	@Autowired
	NoticeService noticeService;

	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public Response<Notice> getAll() {
		Response<Notice> response = new Response<Notice>();
		try {
			List<Notice> result = noticeService.getAll();
			response.setData(result);
			response.setMessage("All Notice Lists");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal Server Error");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "getAllByNoticeStatus", method = RequestMethod.GET)
	public Response<Notice> getAllByNoticeStatus() {
		Response<Notice> response = new Response<Notice>();
		try {
			List<Notice> result = noticeService.getAllByNoticeStatus();
			response.setData(result);
			response.setMessage("All unread Notice Lists");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal Server Error");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public Response<Notice> getById(@RequestParam Long id) {
		Response<Notice> response = new Response<Notice>();
		try {
			Notice result = noticeService.getById(id);
			response.setData(result);
			response.setMessage("Notice with Selected Id");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal Server Error");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Response<Notice> create(@RequestBody Notice notice) {
		Response<Notice> response = new Response<Notice>();
		try {
			notice.setNotice_status(true);
			if (notice.getNoticePicture() == null || notice.getNoticePicture().isEmpty()) {
				notice.setNoticePicture(defaultPhoto);
			}
			Notice result = noticeService.create(notice);
			response.setData(result);
			response.setMessage("Save success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal Server Error");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public Response<Notice> delete(@RequestParam Long id) {
		Response<Notice> response = new Response<Notice>();
		try {
			Notice existingData = noticeService.getById(id);
			if (existingData != null) {
				Notice oldData = existingData;
				oldData.setActiveStatus(ActiveStatus.DELETE);
				oldData.setModifyDate(new Date());
				response.setData(noticeService.create(oldData));
				response.setMessage("Delete succes");
			} else {
				response.setMessage("Record Not Found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server error occur");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Response<Notice> update(@RequestBody Notice notice) {
		Response<Notice> response = new Response<Notice>();
		try {
			Notice existingData = noticeService.getById(notice.getId());
			if (existingData != null) {
				Notice oldData = notice;
				oldData.setNotice_status(false);
				response.setData(noticeService.create(oldData));
				response.setMessage("update succes");
			} else {
				response.setMessage("NO existing Data");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server error occur");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "uploadNoticeFile", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public Response<String> saveNoticeFile(@RequestPart("uploadFile") MultipartFile uploadFile) throws IOException {
		Response<String> response = new Response<String>();
		if (uploadFile == null || uploadFile.isEmpty()) {
			response.setData(defaultPhoto);
			response.setStatus(false);
			response.setMessage("Invalid File Upload!");

		} else {
			String filePath = Util.uploadFile(uploadFile, noticeRealPath, noticeRelativePath);
			response.setData(filePath);
		}
		return response;
	}
}
