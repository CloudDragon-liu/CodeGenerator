import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dragon.codergen.generator.GeneratorFacatory;
import com.dragon.codergen.internal.config.Configuration;
import com.dragon.codergen.utils.DateUtils;
import com.dragon.codergen.utils.Messages;
import com.dragon.codergen.utils.UtilsString;
import com.dragon.codergen.utils.ZipUtil;

public class Runner {
	public static void main(String[] args) throws SQLException, IOException {
		new Runner().run();
	}

	public void run() throws SQLException, IOException {
		Date startTime = new Date();

		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring-context.xml" });

		BeanFactory factory = (BeanFactory) context;

		Configuration config = (Configuration) factory.getBean("config");
		Messages internal = (Messages) factory.getBean("internal");

		// 清空文件
		if ("true".equalsIgnoreCase(config.getEmptybeforefiles().trim())) {
			FileUtils.forceDelete(new File(config.getRealpath()));
		}

		GeneratorFacatory generatorFacatory = (GeneratorFacatory) factory.getBean("generatorFacatory");

		// Generator displays, you could choose unused to note
		generatorFacatory.getDomainGenerator().generate();
		generatorFacatory.getDaoGenerator().generate();
		generatorFacatory.getDaoImplGenerator().generate();
		generatorFacatory.getIbatisSqlMapXmlGenerator().generate();
		generatorFacatory.getServiceGenerator().generate();
		generatorFacatory.getServiceImplGenerator().generate();
		generatorFacatory.getFacadeGenerator().generate();
		generatorFacatory.getFacadeImplGenerator().generate();
		generatorFacatory.getIbatisSqlMapConfigXmlGenerator().generate();

		// if you pick up zip
		if (new Boolean(UtilsString.removeQuote(config.getWorkspace_zip()).trim())) {
			System.out.println(internal.getString("log.zip.going"));

			Date zipStartTime = new Date();
			String zipFilePath = config.getWorkspace() + config.getName() + "_v" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".zip";
			ZipUtil.zip(config.getRealpath(), zipFilePath);
			Date zipEndTime = new Date();

			System.out.println(internal.getString("log.zip.end", DateUtils.getTimeInterval(zipStartTime, zipEndTime), zipFilePath));
		}

		Date endTime = new Date();
		System.out.println(internal.getString("log.generator.run.time", DateUtils.getTimeInterval(startTime, endTime), config.getRealpath()));

	}

}
