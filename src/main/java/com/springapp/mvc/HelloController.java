package com.springapp.mvc;
import com.springapp.mvc.modelsToXml.CstmrPmtStsRpt;
import com.springapp.mvc.models.GrpHdr;
import com.springapp.mvc.models.PmtInf;
import com.springapp.mvc.models.RejTable;
import com.springapp.mvc.modelsToXml.OrgnlGrpInfAndSts;
import com.springapp.mvc.modelsToXml.OrgnlPmtInfAndSts;
import com.springapp.mvc.modelsToXml.OrgnlPmtInfAndStsUnd.*;
import com.springapp.mvc.service.MyServiceClass;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.commons.io.FilenameUtils;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/")
public class HelloController {
	@Autowired
	MyServiceClass service;
	@Autowired
	private ApplicationContext appContext;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		DriverManagerDataSource driverManagerDataSource = (DriverManagerDataSource)appContext.getBean("dataSource");
		model.addAttribute("resultUpload","");
		model.addAttribute("fileSave","");
		httpSession.setAttribute("userWhichWrite",driverManagerDataSource.getUsername());
		return "sendXML";


	}


	@RequestMapping(value = "getlist",method = RequestMethod.GET)
	public String getlist(ModelMap model) {
		List<PmtInf> list = service.getlistPmtInf();
		model.addAttribute("list", list);
		return "allpmt";
	}
	@ResponseBody
	@RequestMapping(value = "okhttp",method = RequestMethod.GET)
	public String testokhttp(@RequestParam(value = "url",required = false)String url) {
		if(url!=null){
			OkHttpClient client = new OkHttpClient();
			String body = null;
			Request request = new Request.Builder()
					.url(url)
					.build();
			try {
				Response response = client.newCall(request).execute();
				body = response.body().string();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return body;
		}else{
			OkHttpClient client = new OkHttpClient();
			String body = null;
			Request request = new Request.Builder()
					.url("https://aiylbank.herokuapp.com/rest/rest")
					.build();
			try {
				Response response = client.newCall(request).execute();
				body = response.body().string();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return body;
		}

	}

	@RequestMapping(value = "okhttp2",method = RequestMethod.GET)
	public String testokhttp(ModelMap modelMap) {
		OkHttpClient client = new OkHttpClient();
		String body = null;
		Request request = new Request.Builder()
				.url("https://aiylbank.herokuapp.com/rest/rest")
				.build();
		try {
			Response response = client.newCall(request).execute();
			body = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("gson",body);
		return "RequestToFognRes";
	}
	@RequestMapping(value = "getlistRejTable",method = RequestMethod.GET)
	public String getlistRejTable(ModelMap model) {
		List<RejTable> rejTables = service.getRejTables();
		model.addAttribute("rejTables",rejTables);
		return "allRejTable";
	}
	@RequestMapping(value = "generate",method = RequestMethod.GET)
	public String generate(@RequestParam("pmtId") String pmtId,@RequestParam("statusPmt") String statusPmt,ModelMap model) {
    	RejTable rejTable = new RejTable();
		rejTable.setRej_code(statusPmt);
		rejTable.setDone("");
		rejTable.setP_id(Long.valueOf(pmtId));
		service.save(rejTable);
		List<RejTable> rejTables = service.getRejTables();
		model.addAttribute("rejTables",rejTables);
		return "allRejTable";
	}
	@ResponseBody
	@RequestMapping(value = "generate-xml",method = RequestMethod.GET)
	public Object generateXml(@RequestParam("r_id") String r_id) {
		RejTable rejTable = service.getRejTable(Long.valueOf(r_id));
		CstmrPmtStsRpt cstmrPmtStsRpt = new CstmrPmtStsRpt();
		com.springapp.mvc.modelsToXml.GrpHdr grpHdr = new com.springapp.mvc.modelsToXml.GrpHdr();
		com.springapp.mvc.modelsToXml.Id id = new com.springapp.mvc.modelsToXml.Id();
		com.springapp.mvc.modelsToXml.InitgPty initgPty = new com.springapp.mvc.modelsToXml.InitgPty();
		com.springapp.mvc.modelsToXml.OrgId orgId = new com.springapp.mvc.modelsToXml.OrgId();

		orgId.setBICOrBEI("advadvdsv");
		id.setOrgId(orgId);
		initgPty.setId(id);
		grpHdr.setInitgPty(initgPty);
		grpHdr.setMsgId("dsdsbsfbvdsdvdsvd");
		grpHdr.setCreDtTm(new Date().toString());
		cstmrPmtStsRpt.setGrpHdr(grpHdr);

		OrgnlGrpInfAndSts orgnlGrpInfAndSts = new OrgnlGrpInfAndSts();
		PmtInf pmtInf = service.getPmtInfById(rejTable.getP_id());
		com.springapp.mvc.models.GrpHdr grpHdr1 = service.getGrpHdrById(pmtInf.getGrpHdr().getGrpHdr_id()) ;

		orgnlGrpInfAndSts.setOrgnlMsgId(grpHdr1.getMsgId()+"");
		orgnlGrpInfAndSts.setOrgnlNbOfTxs(grpHdr1.getNbOfTxs() + "");
		orgnlGrpInfAndSts.setOrgnlCtrlSum(grpHdr1.getCtrlSum() + "");
		orgnlGrpInfAndSts.setOrgnlMsgNmId(grpHdr1.getNm() + "");
		cstmrPmtStsRpt.setOrgnlGrpInfAndSts(orgnlGrpInfAndSts);
		return cstmrPmtStsRpt;
	}

	@ResponseBody
	@RequestMapping(value = "generate-xml-from-rejtable",method = RequestMethod.GET)
	public Object generateXmlFromRejtable() {
		List<RejTable>  rejTables = service.generateXmlFromRejtable();
		CstmrPmtStsRpt cstmrPmtStsRpt = new CstmrPmtStsRpt();
		if(rejTables!=null&&rejTables.size()!=0){
			RejTable rejTable = rejTables.get(0);


			com.springapp.mvc.modelsToXml.GrpHdr grpHdr = new com.springapp.mvc.modelsToXml.GrpHdr();
			com.springapp.mvc.modelsToXml.Id id = new com.springapp.mvc.modelsToXml.Id();
			com.springapp.mvc.modelsToXml.InitgPty initgPty = new com.springapp.mvc.modelsToXml.InitgPty();
			com.springapp.mvc.modelsToXml.OrgId orgId = new com.springapp.mvc.modelsToXml.OrgId();

			orgId.setBICOrBEI("ALTYNBEK");
			id.setOrgId(orgId);
			initgPty.setId(id);
			grpHdr.setInitgPty(initgPty);
			grpHdr.setMsgId("ALTYNBEK MSG");
			grpHdr.setCreDtTm(new Date().toString());
			cstmrPmtStsRpt.setGrpHdr(grpHdr);

			OrgnlGrpInfAndSts orgnlGrpInfAndSts = new OrgnlGrpInfAndSts();
			PmtInf ptInf = service.getPmtInfById(rejTable.getP_id());
			com.springapp.mvc.models.GrpHdr grpHdr1 = service.getGrpHdrById(ptInf.getGrpHdr().getGrpHdr_id());

			orgnlGrpInfAndSts.setOrgnlMsgId(grpHdr1.getMsgId() + "");
			orgnlGrpInfAndSts.setOrgnlNbOfTxs(grpHdr1.getNbOfTxs() + "");
			orgnlGrpInfAndSts.setOrgnlCtrlSum(grpHdr1.getCtrlSum() + "");
			orgnlGrpInfAndSts.setOrgnlMsgNmId(grpHdr1.getNm() + "");
			orgnlGrpInfAndSts.setGrpSts("RJCT");
			List<TxInfAndSts> txInfAndSts = new ArrayList<>();
			for(int i =0;i<rejTables.size();i++){
				rejTable = rejTables.get(i);
				PmtInf pmtInf = service.getPmtInfById(rejTable.getP_id());
				TxInfAndSts txInfAndSts1 = new TxInfAndSts();
				txInfAndSts1.setOrgnlEndToEndId(pmtInf.getCdTrTxEndId());
				txInfAndSts1.setTxSts("RJCT");
				StsRsnInf stsRsnInf  =  new StsRsnInf();
				Rsn rsn = new Rsn();
				rsn.setCd(rejTable.getRej_code());
				stsRsnInf.setAddtlInf("Opisanie statusa owibki");
				stsRsnInf.setRsn(rsn);
				txInfAndSts1.setStsRsnInf(stsRsnInf);
				OrgnlTxRef orgnlTxRef = new OrgnlTxRef();
				Amt amt = new Amt();
				amt.setInstdAmt(pmtInf.getCtrlSum());
				orgnlTxRef.setAmt(amt);
				orgnlTxRef.setReqdExctnDt(pmtInf.getReqdExctnDt());
				txInfAndSts1.setOrgnlTxRef(orgnlTxRef);

				txInfAndSts.add(txInfAndSts1);
			}
			OrgnlPmtInfAndSts orgnlPmtInfAndSts = new OrgnlPmtInfAndSts();
			orgnlPmtInfAndSts.setOrgnlPmtInfId(ptInf.getPmtInfId());
			orgnlPmtInfAndSts.setPmtInfSts("RJCT");
			orgnlPmtInfAndSts.setTxInfAndSts(txInfAndSts);
			cstmrPmtStsRpt.setOrgnlPmtInfAndSts(orgnlPmtInfAndSts);
			cstmrPmtStsRpt.setOrgnlGrpInfAndSts(orgnlGrpInfAndSts);
		}


		return cstmrPmtStsRpt;
	}

	@ResponseBody
	@RequestMapping(value = "generate-xml-from-rejtable-done-is-null",method = RequestMethod.GET)
	public Object generateXmlFromRejtableDoneIsNull() {
		List<Long> pmtInfsId = new ArrayList<>();
		List<PmtInf> acceptedPmtInfs = null;

		int  pmtSize = service.getSizePmtInf();
		List<RejTable>  rejTables = service.getLastRejTables();
		GrpHdr  lastGrpHdr = service.getLastData();
		String partOrRjct = "";
		if(pmtSize!=0&&rejTables.size()!=0&&pmtSize==rejTables.size()){
			partOrRjct="RJCT";
		}else if(pmtSize>rejTables.size()&&rejTables.size()!=0){
			partOrRjct="PART";
			for(int i=0;i<rejTables.size();i++){
				pmtInfsId.add(rejTables.get(i).getP_id());
			}
			acceptedPmtInfs = service.getAcceptedPmtInfs(lastGrpHdr.getGrpHdr_id(), pmtInfsId);
		}else{
			partOrRjct="ACCP";
		}
		CstmrPmtStsRpt cstmrPmtStsRpt = new CstmrPmtStsRpt();
		if(!partOrRjct.equals("ACCP")){
			if(rejTables!=null&&rejTables.size()!=0){
				RejTable rejTable = rejTables.get(0);


				com.springapp.mvc.modelsToXml.GrpHdr grpHdr = new com.springapp.mvc.modelsToXml.GrpHdr();
				com.springapp.mvc.modelsToXml.Id id = new com.springapp.mvc.modelsToXml.Id();
				com.springapp.mvc.modelsToXml.InitgPty initgPty = new com.springapp.mvc.modelsToXml.InitgPty();
				com.springapp.mvc.modelsToXml.OrgId orgId = new com.springapp.mvc.modelsToXml.OrgId();

				orgId.setBICOrBEI("ALTYNBEK");
				id.setOrgId(orgId);
				initgPty.setId(id);
				grpHdr.setInitgPty(initgPty);
				grpHdr.setMsgId("ALTYNBEK MSG");
				grpHdr.setCreDtTm(new Date().toString());
				cstmrPmtStsRpt.setGrpHdr(grpHdr);

				OrgnlGrpInfAndSts orgnlGrpInfAndSts = new OrgnlGrpInfAndSts();
				PmtInf ptInf = service.getPmtInfById(rejTable.getP_id());
				com.springapp.mvc.models.GrpHdr grpHdr1 = service.getGrpHdrById(ptInf.getGrpHdr().getGrpHdr_id());

				orgnlGrpInfAndSts.setOrgnlMsgId(grpHdr1.getMsgId() + "");
				orgnlGrpInfAndSts.setOrgnlNbOfTxs(grpHdr1.getNbOfTxs() + "");
				orgnlGrpInfAndSts.setOrgnlCtrlSum(grpHdr1.getCtrlSum() + "");
				orgnlGrpInfAndSts.setOrgnlMsgNmId(grpHdr1.getNm() + "");
				orgnlGrpInfAndSts.setGrpSts(partOrRjct);
				List<TxInfAndSts> txInfAndSts = new ArrayList<>();
				for(int i =0;i<rejTables.size();i++){
					rejTable = rejTables.get(i);
					PmtInf pmtInf = service.getPmtInfById(rejTable.getP_id());
					TxInfAndSts txInfAndSts1 = new TxInfAndSts();
					txInfAndSts1.setOrgnlEndToEndId(pmtInf.getCdTrTxEndId());
					txInfAndSts1.setTxSts("RJCT");
					StsRsnInf stsRsnInf  =  new StsRsnInf();
					Rsn rsn = new Rsn();
					rsn.setCd(rejTable.getRej_code());
					stsRsnInf.setAddtlInf("Opisanie statusa owibki");
					stsRsnInf.setRsn(rsn);
					txInfAndSts1.setStsRsnInf(stsRsnInf);
					OrgnlTxRef orgnlTxRef = new OrgnlTxRef();
					Amt amt = new Amt();
					amt.setInstdAmt(pmtInf.getCtrlSum());
					orgnlTxRef.setAmt(amt);
					orgnlTxRef.setReqdExctnDt(pmtInf.getReqdExctnDt());
					txInfAndSts1.setOrgnlTxRef(orgnlTxRef);

					txInfAndSts.add(txInfAndSts1);
				}
				if(acceptedPmtInfs!=null){
					for(int i =0;i<acceptedPmtInfs.size();i++){
						PmtInf pmtInf = acceptedPmtInfs.get(i);
						TxInfAndSts txInfAndSts1 = new TxInfAndSts();
						txInfAndSts1.setOrgnlEndToEndId(pmtInf.getCdTrTxEndId());
						txInfAndSts1.setTxSts("ACCP");
						StsRsnInf stsRsnInf  =  new StsRsnInf();
						Rsn rsn = new Rsn();
						stsRsnInf.setAddtlInf("Opisanie statusa owibki");
						stsRsnInf.setRsn(rsn);
						txInfAndSts1.setStsRsnInf(stsRsnInf);
						OrgnlTxRef orgnlTxRef = new OrgnlTxRef();
						Amt amt = new Amt();
						amt.setInstdAmt(pmtInf.getCtrlSum());
						orgnlTxRef.setAmt(amt);
						orgnlTxRef.setReqdExctnDt(pmtInf.getReqdExctnDt());
						txInfAndSts1.setOrgnlTxRef(orgnlTxRef);

						txInfAndSts.add(txInfAndSts1);
					}
				}

				OrgnlPmtInfAndSts orgnlPmtInfAndSts = new OrgnlPmtInfAndSts();
				orgnlPmtInfAndSts.setOrgnlPmtInfId(ptInf.getPmtInfId());
				orgnlPmtInfAndSts.setPmtInfSts(partOrRjct);
				orgnlPmtInfAndSts.setTxInfAndSts(txInfAndSts);
				cstmrPmtStsRpt.setOrgnlPmtInfAndSts(orgnlPmtInfAndSts);
				cstmrPmtStsRpt.setOrgnlGrpInfAndSts(orgnlGrpInfAndSts);
				service.updatePmtInfStatuses();
			}else{
				cstmrPmtStsRpt = new CstmrPmtStsRpt();
			}
		}else{

			com.springapp.mvc.modelsToXml.GrpHdr grpHdr = new com.springapp.mvc.modelsToXml.GrpHdr();
			com.springapp.mvc.modelsToXml.Id id = new com.springapp.mvc.modelsToXml.Id();
			com.springapp.mvc.modelsToXml.InitgPty initgPty = new com.springapp.mvc.modelsToXml.InitgPty();
			com.springapp.mvc.modelsToXml.OrgId orgId = new com.springapp.mvc.modelsToXml.OrgId();

			orgId.setBICOrBEI("ALTYNBEK");
			id.setOrgId(orgId);
			initgPty.setId(id);
			grpHdr.setInitgPty(initgPty);
			grpHdr.setMsgId("AL1010101010");
			grpHdr.setCreDtTm(new Date().toString());
			cstmrPmtStsRpt.setGrpHdr(grpHdr);

			OrgnlGrpInfAndSts orgnlGrpInfAndSts = new OrgnlGrpInfAndSts();
			PmtInf ptInf = service.getLastPmtInf();
			com.springapp.mvc.models.GrpHdr grpHdr1 = service.getGrpHdrById(ptInf.getGrpHdr().getGrpHdr_id());

			orgnlGrpInfAndSts.setOrgnlMsgId(grpHdr1.getMsgId() + "");
			orgnlGrpInfAndSts.setOrgnlNbOfTxs(grpHdr1.getNbOfTxs() + "");
			orgnlGrpInfAndSts.setOrgnlCtrlSum(grpHdr1.getCtrlSum() + "");
			orgnlGrpInfAndSts.setOrgnlMsgNmId(grpHdr1.getNm() + "");
			orgnlGrpInfAndSts.setGrpSts(partOrRjct);
			List<TxInfAndSts> txInfAndSts = new ArrayList<>();
			acceptedPmtInfs = service.getAcceptedPmtInfs(grpHdr1.getGrpHdr_id());
			if(acceptedPmtInfs!=null&&acceptedPmtInfs.size()!=0){
				for(int i =0;i<acceptedPmtInfs.size();i++){
					PmtInf pmtInf = acceptedPmtInfs.get(i);
					TxInfAndSts txInfAndSts1 = new TxInfAndSts();
					txInfAndSts1.setOrgnlEndToEndId(pmtInf.getCdTrTxEndId());
					txInfAndSts1.setTxSts("ACCP");
					StsRsnInf stsRsnInf  =  new StsRsnInf();
					Rsn rsn = new Rsn();
					stsRsnInf.setAddtlInf("ACCP");
					stsRsnInf.setRsn(rsn);
					txInfAndSts1.setStsRsnInf(stsRsnInf);
					OrgnlTxRef orgnlTxRef = new OrgnlTxRef();
					Amt amt = new Amt();
					amt.setInstdAmt(pmtInf.getCtrlSum());
					orgnlTxRef.setAmt(amt);
					orgnlTxRef.setReqdExctnDt(pmtInf.getReqdExctnDt());
					txInfAndSts1.setOrgnlTxRef(orgnlTxRef);

					txInfAndSts.add(txInfAndSts1);
				}
				OrgnlPmtInfAndSts orgnlPmtInfAndSts = new OrgnlPmtInfAndSts();
				orgnlPmtInfAndSts.setOrgnlPmtInfId(ptInf.getPmtInfId());
				orgnlPmtInfAndSts.setPmtInfSts(partOrRjct);
				orgnlPmtInfAndSts.setTxInfAndSts(txInfAndSts);
				cstmrPmtStsRpt.setOrgnlPmtInfAndSts(orgnlPmtInfAndSts);
				cstmrPmtStsRpt.setOrgnlGrpInfAndSts(orgnlGrpInfAndSts);
				service.updatePmtInfStatuses();
			}else{
				cstmrPmtStsRpt = new CstmrPmtStsRpt();
			}


		}

        service.updateRejTableStatuses();


		return cstmrPmtStsRpt;
	}

	@RequestMapping(value = "upload-xml",method = RequestMethod.POST)
	public String xmlParser(@RequestParam("file") MultipartFile xml,Model model,HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		String userWhichWrite = (String)httpSession.getAttribute("userWhichWrite");
		String rootPath = null;
		String file_name = null;
		String file_ext = null;
		MultipartFile file = xml;
		file_name = xml.getOriginalFilename();
		file_ext = FilenameUtils.getExtension(file_name);
		StringBuilder stringBuilder = new StringBuilder();
		try {
			byte[] bytes = file.getBytes();
			rootPath = "C:"+ File.separator+"XMLS"+File.separator;
			File dir = new File(rootPath + File.separator);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File serverFile = new File(dir.getAbsolutePath()
					+ File.separator + "myxml." + file_ext);
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile));
			stream.write(bytes);
			stringBuilder.append("myxml." + file_ext);
			stream.close();
		} catch (Exception e) {
			return null;
		}

		if(rootPath!=null&&stringBuilder.length()!=0){
			boolean result = parseAndSaveXml(rootPath+stringBuilder,model,userWhichWrite);
			if(result){
				model.addAttribute("fileSave","success");
			}else{
				model.addAttribute("fileSave","error");
			}
		}
		model.addAttribute("resultUpload","good");
		return "sendXML";


	}
	@RequestMapping(value = "remove-data",method = RequestMethod.GET)
	public String removeData(Model model) {
        service.removeLastData();

		model.addAttribute("fileSave","removed");
		return "sendXML";


	}


	public boolean parseAndSaveXml(String path,Model model,String userWhichUpload){
		GrpHdr grpHdr = new GrpHdr();
		List<PmtInf> pmtInflist= new ArrayList<>();

		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(path);

		try {

			Document document = (Document) builder.build(xmlFile);

			Element root = document.getRootElement();
			Namespace ns = root.getNamespace();

			List childs = document.getRootElement().getChildren();
			Element cstmrCdtTrfInitn = (Element) childs.get(0);
			List list = cstmrCdtTrfInitn.getChildren();



			float CtrlSumPmt=0f;

			for (int i = 0; i < list.size(); i++) {
				PmtInf pmtInf = new PmtInf();

				Element node = (Element) list.get(i);
				if(node.getName().equals("PmtInf")){

					try{
						pmtInf.setPmtInfId(Long.valueOf(node.getChildText("PmtInfId", ns)));
					}catch (NullPointerException e){}
					try{
						pmtInf.setPmtMtd(node.getChildText("PmtMtd", ns));
					}catch (NullPointerException e){}
					try{
						pmtInf.setBtchBookg(Boolean.valueOf(node.getChildText("BtchBookg", ns)));
					}catch (NullPointerException e){}
					try{
						pmtInf.setNbOfTxs(Integer.valueOf(node.getChildText("NbOfTxs", ns)));
					}catch (NullPointerException e){}
					try{
						pmtInf.setCtrlSum(Float.valueOf(node.getChildText("CtrlSum", ns)));
						CtrlSumPmt=CtrlSumPmt+pmtInf.getCtrlSum();
					}catch (NullPointerException e){}
					try{
						pmtInf.setPrtry(node.getChild("PmtTpInf", ns).getChild("SvcLvl", ns).getChildText("Prtry", ns));
					}catch (NullPointerException e){}
					try{
						pmtInf.setChrgBr(node.getChildText("ChrgBr", ns));
					}catch (NullPointerException e){}
					try{
						pmtInf.setReqdExctnDt(node.getChildText("ReqdExctnDt", ns));
					}catch (NullPointerException e){}
					try{
						pmtInf.setDbtrNm(node.getChild("Dbtr", ns).getChildText("Nm", ns));
					}catch (NullPointerException e){e.printStackTrace();}
					try{
						String s = node.getChild("Dbtr", ns).getChild("PstlAdr", ns).getChildren().get(0).getText();
						pmtInf.setDbtrAdr(s);
					}catch (NullPointerException e){}
					try{
						String s = node.getChild("Dbtr", ns).getChild("PstlAdr", ns).getChildren().get(1).getText();
						pmtInf.setDbtrAdr2(s);
					}catch (NullPointerException e){e.printStackTrace();}
					try{
						pmtInf.setDbtrOthrId(Long.valueOf(node.getChild("Dbtr", ns).getChild("Id", ns).getChild("PrvtId", ns).getChild("Othr", ns).getChildText("Id", ns)));
					}catch (NullPointerException e){}
					try{
						pmtInf.setDbtrOthrCd(node.getChild("Dbtr", ns).getChild("Id", ns).getChild("PrvtId", ns).getChild("Othr", ns).getChild("SchmeNm", ns).getChildText("Cd", ns));
					}catch (NullPointerException e){}

					try{
						pmtInf.setDbtrOthrIssr(node.getChild("Dbtr", ns).getChild("Id", ns).getChild("PrvtId", ns).getChild("Othr", ns).getChildText("Issr", ns));
					}catch (NullPointerException e){}




					try{
						pmtInf.setDbtrAcctIBAN(node.getChild("DbtrAcct", ns).getChild("Id", ns).getChildText("IBAN", ns));
					}catch (NullPointerException e){}



					try{
						pmtInf.setDbtrAgtMmbId(node.getChild("DbtrAgt", ns).getChild("FinInstnId", ns).getChild("ClrSysMmbId", ns).getChildText("MmbId", ns));
					}catch (NullPointerException e){}





					try{
						pmtInf.setCdTrTxEndId(node.getChild("CdtTrfTxInf", ns).getChild("PmtId", ns).getChildText("EndToEndId", ns));
					}catch (NullPointerException e){}



					try{
						pmtInf.setCdTrTxInstAmt(Float.valueOf(node.getChild("CdtTrfTxInf", ns).getChild("Amt", ns).getChildText("InstdAmt", ns)));
					}catch (NullPointerException e){}




					try{
						pmtInf.setCdTxCdtrAgtMbId(node.getChild("CdtTrfTxInf", ns).getChild("CdtrAgt", ns).getChild("FinInstnId", ns).getChild("ClrSysMmbId", ns).getChildText("MmbId", ns));
					}catch (NullPointerException e){}


					try{
						pmtInf.setCdtrNm(node.getChild("CdtTrfTxInf", ns).getChild("Cdtr", ns).getChildText("Nm", ns));
					}catch (NullPointerException e){}
					try{
						pmtInf.setCdtrAdr(node.getChild("CdtTrfTxInf", ns).getChild("Cdtr", ns).getChild("PstlAdr", ns).getChildText("AdrLine", ns));
					}catch (NullPointerException e){}
					try{
						pmtInf.setCdtrPrvBirthDt(node.getChild("CdtTrfTxInf", ns).getChild("Cdtr", ns).getChild("Id", ns).getChild("PrvtId", ns).getChild("DtAndPlcOfBirth", ns).getChildText("BirthDt", ns));
					}catch (NullPointerException e){}
					try{
						pmtInf.setCdtrPrvCitBth(node.getChild("CdtTrfTxInf", ns).getChild("Cdtr", ns).getChild("Id", ns).getChild("PrvtId", ns).getChild("DtAndPlcOfBirth", ns).getChildText("CityOfBirth", ns));
					}catch (NullPointerException e){}
					try{
						pmtInf.setCdtrPrvCtrBth(node.getChild("CdtTrfTxInf", ns).getChild("Cdtr", ns).getChild("Id", ns).getChild("PrvtId", ns).getChild("DtAndPlcOfBirth", ns).getChildText("CtryOfBirth", ns));
					}catch (NullPointerException e){}
					try{
						pmtInf.setCdtrPrvOthId(Long.valueOf(node.getChild("CdtTrfTxInf", ns).getChild("Cdtr", ns).getChild("Id", ns).getChild("PrvtId", ns).getChild("Othr", ns).getChildText("Id", ns)));
					}catch (NullPointerException e){}
					try{
						pmtInf.setCdtrPrvOthIssr(node.getChild("CdtTrfTxInf", ns).getChild("Cdtr", ns).getChild("Id", ns).getChild("PrvtId", ns).getChild("Othr", ns).getChildText("Issr", ns));
					}catch (NullPointerException e){}
					try{
						pmtInf.setCdtrPrvOthCd(node.getChild("CdtTrfTxInf", ns).getChild("Cdtr", ns).getChild("Id", ns).getChild("PrvtId", ns).getChild("Othr", ns).getChild("SchmeNm", ns).getChildText("Cd", ns));
					}catch (NullPointerException e){}




					try{
						pmtInf.setCdtrAcctIBAN(node.getChild("CdtTrfTxInf", ns).getChild("CdtrAcct", ns).getChild("Id", ns).getChildText("IBAN", ns));
					}catch (NullPointerException e){}


					try{
						pmtInf.setRmtInfUstrd(node.getChild("CdtTrfTxInf", ns).getChild("RmtInf", ns).getChildText("Ustrd", ns));
					}catch (NullPointerException e){}



					pmtInf.setGrpHdr(grpHdr);
					pmtInflist.add(pmtInf);


				}else if(node.getName().equals("GrpHdr")){
					try{
						grpHdr.setMsgId(Long.valueOf(node.getChildText("MsgId", ns)));
					}catch (NullPointerException e){}
					try{
						grpHdr.setCreDtTm(node.getChildText("CreDtTm", ns));
					}catch (NullPointerException e){}
					try{
						grpHdr.setNbOfTxs(Integer.valueOf(node.getChildText("NbOfTxs", ns)));
					}catch (NullPointerException e){}
					try{
						grpHdr.setCtrlSum(Float.valueOf(node.getChildText("CtrlSum", ns)));
					}catch (NullPointerException e){}
					try{
						grpHdr.setNm(node.getChild("InitgPty", ns).getChildText("Nm",ns));
					}catch (NullPointerException e){}
				}
			}
			grpHdr.setPmtInfs(pmtInflist);
			grpHdr.setWrUser(userWhichUpload);
			grpHdr.setDateWrite(new Date().toString());
			model.addAttribute("CtrlSumGrp", grpHdr.getCtrlSum() + "");
			model.addAttribute("CtrlSumPmt",CtrlSumPmt+"");
			return service.save(grpHdr);

		} catch (IOException io) {} catch (JDOMException jdomex) {}
		return false;
	}



	}
