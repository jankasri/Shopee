package com.forex.endpoint;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forex.entity.ForexEntity;
import com.forex.entity.RateDailyEntity;
import com.forex.form.ForexForm;
import com.forex.service.ForexService;
import com.forex.service.RateDailyService;
import com.google.gson.JsonObject;

@RestController
public class ForexEndpoint {
	/**
	 ** Jankasri Silalahi
	 **/

	@Autowired
	private ForexService forexService;

	@Autowired
	private RateDailyService rateDailyService;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private String CODE_OK = "00";
	private String CODE_ERROR = "99";
	private String CODE_ERROR_FOREX_NOT_FOUND = "01";

	private String MSG_OK = "OK";
	private String MSG_ERROR = "System error";
	private String MSG_ERROR_FOREX_NOT_FOUND = "Forex not Found";
	private String SYSTEM = "SYSTEM";

	@PostMapping("/addExchangeRate")
	public String addExchangeRate(@RequestBody String request) {
		try {
			log.info("Request addExchangeRate : " + prettyPrinter(request, ForexEntity.class));

			ForexEntity forex = getEntity(request, ForexEntity.class);

			forex.setCreatedBy(SYSTEM);
			forex.setCreatedDate(new Date());
			forexService.create(forex);
		} catch (Exception e) {
			e.printStackTrace();
			return response(CODE_ERROR, MSG_ERROR);
		}
		return response(CODE_OK, MSG_OK);
	}

	@DeleteMapping("/deleteExchangeRate")
	public String deleteExchangeRate(@RequestBody String request) {
		try {
			log.info("Request addExchangeRate : " + prettyPrinter(request, ForexEntity.class));

			ForexEntity form = getEntity(request, ForexEntity.class);

			ForexEntity forex = forexService.findByFromAndTo(form.getFrom(), form.getTo());

			if (forex == null) {
				return response(CODE_ERROR_FOREX_NOT_FOUND, MSG_ERROR_FOREX_NOT_FOUND);
			}

			List<RateDailyEntity> rateList = rateDailyService.findByForex(forex);

			rateDailyService.deleteByRateList(rateList);

			forexService.deleteByFromAndTo(forex.getFrom(), forex.getTo());
		} catch (Exception e) {
			e.printStackTrace();
			return response(CODE_ERROR, MSG_ERROR);
		}
		return response(CODE_OK, MSG_OK);
	}

	@PostMapping("/addDailyRate")
	public String addDailyRate(@RequestBody String request) {
		try {
			log.info("Request addDailyRate : " + prettyPrinter(request, ForexForm.class));

			ForexForm form = getEntity(request, ForexForm.class);

			ForexEntity forex = forexService.findByFromAndTo(form.getFrom(), form.getTo());

			if (forex == null) {
				return response(CODE_ERROR_FOREX_NOT_FOUND, MSG_ERROR_FOREX_NOT_FOUND);
			}

			RateDailyEntity rate = new RateDailyEntity(form.getRate(), form.getDate(), forex);

			rateDailyService.create(rate);
		} catch (Exception e) {
			e.printStackTrace();
			return response(CODE_ERROR, MSG_ERROR);
		}
		return response(CODE_OK, MSG_OK);
	}

	@PostMapping("/listRate")
	public String listRate(@RequestBody String request) {
		List<ForexForm> formList = new ArrayList<ForexForm>();
		try {
			log.info("Request listRate : " + prettyPrinter(request, ForexForm.class));

			ForexForm form = getEntity(request, ForexForm.class);

			List<ForexEntity> forexList = forexService.findAll();

			for (ForexEntity forex : forexList) {
				List<RateDailyEntity> rateList = rateDailyService.findByDateBetweenAndForex(getLastSevenDay(form.getDate()), form.getDate(), forex);
				log.info("Size : " + rateList.size());

				BigDecimal rate = new BigDecimal("0");
				if (rateList.size() >= 7) {
					for (RateDailyEntity rateDaily : rateList) {
						rate = rate.add(rateDaily.getRate());
					}
					rate = rate.divide(new BigDecimal(7), 5, RoundingMode.HALF_EVEN);
				}

				ForexForm forexForm = new ForexForm(forex.getFrom(), forex.getTo(), rate);
				formList.add(forexForm);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return response(CODE_ERROR, MSG_ERROR);
		}
		return getJSONString(formList);
	}

	private Date getLastSevenDay(Date date) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, -7);
			return cal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String response(String respCode, String respMsg) {
		try {
			JsonObject json = new JsonObject();
			json.addProperty("respCode", respCode);
			json.addProperty("respMsg", respMsg);
			log.info("Response : " + prettyPrinter(json.toString(), Object.class));
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private <T> T getEntity(String request, Class<T> classType) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			return mapper.readValue(request, classType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private <T> String prettyPrinter(String jsonString, Class<T> valueType) {
		ObjectMapper mapper = new ObjectMapper();
		Object json;
		String result = "";
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			json = mapper.readValue(jsonString, valueType);
			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static String getJSONString(Object classJSON) {
		ObjectMapper mapper = new ObjectMapper();
		OutputStream os = new ByteArrayOutputStream();
		try {
			mapper.writeValue(os, classJSON);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return os.toString();
	}
}
