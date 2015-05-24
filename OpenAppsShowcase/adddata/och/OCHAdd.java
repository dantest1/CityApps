package och;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import ro.mysmartcity.bean.Base;
import ro.mysmartcity.bean.Event;
import ro.mysmartcity.bean.Organization;
import ro.mysmartcity.bean.OrganizationEntity;
import ro.mysmartcity.bean.Project;
import ro.mysmartcity.bean.User;
import ro.mysmartcity.bean.UserEntity;

public class OCHAdd {

	public static void main(String[] args) throws Exception {

		Event e = new Event();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		e.setName("Open Culture Hackathon, Timisoara 2015");
		e.setCity("Timisoara");
		e.setDescription("Timisoara Open Culture Hackathon este primul hackathon din Romania care valorifica date culturale deschise; este o provocare la care sunt invitati sa raspunda programatori, artisti, designeri si institutii de cultura; este un maraton al creativitatii in reutilizarea datelor culturale si o demonstratie de schimbare a viziunii despre interactiunea publicului cu obiectele de arta");
		e.setStartDate(sdf.parse("18/04/2015 10:00"));
		e.setEndDate(sdf.parse("19/04/2015 18:00"));
		e.setLocation("Biblioteca Centrala Universitara 'Eugen Todoran'");
		e.setStatus(Base.STATUS.ACTIVE);

		String eventurl = persistEvent(e);

		Organization o = new Organization();
		o.setName("Smart City Association");
		o.setWebsite("www.mysmartcity.ro");
		o.setDescription("Implicarea cetatenilor in actul de guvernare locala conduce catre orase inteligente si prospere, spre beneficiul locuitorilor lor. Asociatia Smart City este promovata, in tara si in afara ei, ca model de buna practica pentru rezultatele obtinute in domeniul datelor deschise, solutiilor inteligente si educatiei pe aceste doua domenii. in anul 2014 asociatia a fost co-organizator al celui mai mare hackathon din SSE Europei, care a avut o sectiune dedicata datelor deschise si solutiilor de tip \"Smart City\", iar la sfarsitul anului a fost premiata de Guvernul Romaniei ca cea mai activa organizatie in promovarea transparentei in administratia locala. Asociatia este membru al Coalitiei pentru date deschise");
		o.setFacebook("www.facebook.com/mysmartcity.ro");
		String ourl = persistOrganization(o);
		addOE(eventurl, ourl);

		o = new Organization();
		o.setName("Coalitia pentru Date Deschise");
		o.setWebsite("www.datedeschise.fundatia.ro/sample-page/");
		o.setDescription("Coalitia pentru date deschise este o platforma a peste 30 de organizatiilor, institutii universitare si cetateni care promoveaza publicarea datelor detinute de institutiile publice in format deschis si reutilizarea seturilor de date in folosul cetatenilor.");
		o.setFacebook(null);
		ourl = persistOrganization(o);
		addOE(eventurl, ourl);

		o = new Organization();
		o.setName("Departamentul pentru Servicii Online si Design");
		o.setWebsite("www.online.gov.ro/index.php/despre/");
		o.setDescription("Departamentul pentru Servicii Online si Design functioneaza in cadrul Cancelariei Primului ministru si are ca principala misiune digitalizarea administratiei publice. infiintarea DSOD a venit ca un raspuns direct fata de nevoia tot mai acuta de transparentizare a activitatii aparatului guvernamental central, precum si fata de necesitatea apropierii factorilor de decizie de cetateni.");
		o.setFacebook(null);
		ourl = persistOrganization(o);
		addOE(eventurl, ourl);

		o = new Organization();
		o.setName("Kosson");
		o.setWebsite("www.kosson.ro");
		o.setDescription("kosson.ro este un nod web in care pot fi gasite interesele de informare, documentare si actiune ale mai multor specialisti din domeniul stiintelor informarii si nu numai. Este si un ambitios plan de a deveni o platforma extinsa pentru a coagula eforturi la nivel balcanic, european si international. kosson.ro s-a nascut acum 10 ani cu dorinta de a deveni un plan deasupra institutiilor, un loc al bunelor practici si al valorilor existente in biblioteci, arhive, muzee si galerii.");
		o.setFacebook(null);
		ourl = persistOrganization(o);
		addOE(eventurl, ourl);

		o = new Organization();
		o.setName("Open Knowledge Romania");
		o.setWebsite("www.ro.okfn.org");
		o.setDescription("Open Knowledge Romania este parte a retelei internationale non-profit Open Knowledge, retea dedicata cunoasterii deschise, compusa din persoane si organizatii care creeaza unelte, promoveaza idei si deruleaza activitati in scopul deschiderii informatiei. La nivel local, Open Knowledge Romania isi propune sa contribuie, alaturi de alte organizatii si persoane, la promovarea si construirea unei culturi nationale bazate pe cunoasterea deschisa. Cele mai importante proiecte ale grupului sunt recensamantul national si recensamantul local al datelor deschise din Romania. Grupul este membru al Coalitiei pentru date deschise.");
		o.setFacebook(null);
		ourl = persistOrganization(o);
		addOE(eventurl, ourl);

		o = new Organization();
		o.setName("Universitatea Politehnica Timisoara");
		o.setWebsite("www.upt.ro");
		o.setDescription("Universitatea Politehnica Timisoara (UPT) este una dintre cele mai mari si binecunoscute universitati tehnice din Europa Centrala si de Est. UPT a fost infiintata in anul 1920, avand constant o traditie si o reputatie binemeritate. Valoarea universitatii este demonstrata prin programele academice de licenta, master si doctorat, prin cercetarea multidisciplinara, prin suportul acordat studentilor, prin rata ridicata de angajare a absolventilor si prin baza materiala cu dotari la cele mai inalte standarde. Universitatea Politehnica Timisoara cuprinde 10 facultati in cadrul carora studiaza anual cca 14 000 de studenti. UPT are aproximativ 700 de cadre didactice si peste 700 de persoane care sunt angajate ca si personal didactic auxiliar si administrativ. UPT este membru fondator al Asociatiei Timisoara - Capitala Culturala Europeana 2021 si membru al Coalitiei pentru date deschise. Centrul de cercetari Multimedia din cadrul UPT, infiintat in anul 1996, desfasoara activitati educationale, de cercetare si de transfer de cunoastere si consultanta in multimedia, toate in contextul integrarii tehnologiilor informatiei si comunicatiilor spre beneficiul societatii.");
		o.setFacebook(null);
		ourl = persistOrganization(o);
		addOE(eventurl, ourl);

		o = new Organization();
		o.setName("Universitatea de Vest Timisoara");
		o.setWebsite("www.uvt.ro");
		o.setDescription("Fondata in 1944, Universitatea de Vest din Timisoara (UVT) este cea mai importanta universitate comprehensiva din vestul tarii. in ultimii 15 ani, UVT a raspuns schimbarilor care au aparut in politica national-educationala, schimbarilor demografice, cerintelor economiei de piata, nevoilor locale si regionale si noilor tehnologii. Aceasta transformare se desfasoara in unsprezece facultati, care ofera o gama variata de programe de formare initiala, masterat, doctorat si cursuri postuniversitare (www.uvt.ro). Unul dintre departamentele reprezentative ale UVT este Departamentul de Informatica al Facultatii de Matematica si Informatica. Acesta are in componenta un Centru de Cercetare in Informatica (calcul paralel si distribuit, inteligenta artificiala si matematica computationala) si, de asemenea, opereaza cel mai puternic centru de calcul de inalta performanta din Romania (research.info.uvt.ro; hpc.uvt.ro). Cum transparenta si inovatia sunt doua elemente cheie care trebuie sa stea la baza oricarei comunitati, UVT este implicata activ in miscarea Date Deschise din Romania prin participarea la initiative si in proiecte de anvengura europeana in domeniu (Share PSI 2.0, SEED), dar si prin organizarea / participarea de / la evenimente importante pe plan local, national si european.");
		o.setFacebook(null);
		ourl = persistOrganization(o);
		addOE(eventurl, ourl);

		String csvFile = "och/OCH.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(csvFile);

		br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {

			// use comma as separator
			String[] project = line.split(cvsSplitBy);

			try {
				Project p = new Project();
				p.setName(project[1]);
				p.setDescription(project[2].replaceAll("\"", ""));
				p.setCategory(project[3]);
				p.setBeneficiaries(project[4].replaceAll("\"", ""));
				p.setOpenDataSeries(project[5].replaceAll("\"", ""));
				p.setFunctionalities(project[6].replaceAll("\"", ""));
				p.setTechnologies(project[47].replaceAll("\"", ""));
				p.setOnlineURL(project[48].replaceAll("\"", ""));
				p.setGitURL(project[49]);
				p.setState(Base.STATE.valueOf(project[50]));
				p.setAward(project[51]);
				p.setStatus(Base.STATUS.ACTIVE);
				p.setLicense(Base.LICENSE.FREE);
				p.setEventURL(eventurl);
				System.out.println(p);
				String projecturl = persistPRoject(eventurl, p);

				User author = new User();
				author.setName(project[8]);
				author.setEmail(project[9]);
				String authorurl = persistUSer(author);
				System.out.println("author: " + author);
				System.out.println("authorurl: " + authorurl);

				UserEntity ue = new UserEntity();
				ue.setEntityURL(projecturl);
				ue.setUserURL(authorurl);
				ue.setRoleInEntity("Autor");
				ue.setRightInEntity(Base.RIGHT.ADMIN);
				persistUserEntity(ue);
				// Manager.sendPost("http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com/userentity", ue);

				int k = 10;
				while (k < 46) {

					User u = new User();
					u.setName(project[k++]);
					u.setEmail(project[k++]);

					if (u.getEmail() != null && !u.getEmail().trim().equals("")) {

						String url = persistUSer(u);

						ue = new UserEntity();
						ue.setEntityURL(projecturl);
						ue.setUserURL(url);
						ue.setRoleInEntity(project[k]);
						ue.setRightInEntity(Base.RIGHT.ADMIN);
						persistUserEntity(ue);
						// Manager.sendPost("http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com/userentity", ue);

						System.out.println(u);
						System.out.println(url);
						System.out.println("role: " + project[k]);
					}

					k++;
				}

				// System.out.println(project.length + "   -   " + Arrays.toString(project));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		br.close();
		System.out.println("Done");

	}

	private static void addOE(String eventurl, String ourl) throws Exception {
		OrganizationEntity oe = new OrganizationEntity();
		oe.setOrganizationURL(ourl);
		oe.setEntityURL(eventurl);
		oe.setRightInEntity(Base.RIGHT.ADMIN);
		oe.setRoleInEntity("Organizator");

		persistOrganizationEntity(oe);
		//
		// Manager.sendPost("http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com/organizationentity", oe);
	}

	private static String persistUserEntity(UserEntity o) throws Exception {

		String url = Manager.sendGet("http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com/userentity" + "/url/userURL="
				+ o.getUserURL().replaceAll("/", "%2F") + "/entityURL=" + o.getEntityURL().replaceAll("/", "%2F") + "/roleInEntity="
				+ o.getRoleInEntity().replaceAll(" ", "%20"));
		if (url != null && !url.trim().equals("")) {

		} else {
			url = Manager.sendPost("http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com/userentity", o);
		}
		return url;
	}

	private static String persistOrganizationEntity(OrganizationEntity o) throws Exception {

		String url = Manager.sendGet("http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com/organizationentity" + "/url/organizationURL="
				+ o.getOrganizationURL().replaceAll("/", "%2F") + "/entityURL=" + o.getEntityURL().replaceAll("/", "%2F") + "/roleInEntity="
				+ o.getRoleInEntity().replaceAll(" ", "%20"));
		if (url != null && !url.trim().equals("")) {

		} else {
			url = Manager.sendPost("http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com/organizationentity", o);
		}
		return url;
	}

	private static String persistOrganization(Organization o) throws Exception {

		String url = Manager.sendGet("http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com/organization" + "/url/name="
				+ o.getName().replaceAll(" ", "%20"));
		if (url != null && !url.trim().equals("")) {

		} else {
			url = Manager.sendPost("http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com/organization", o);
		}
		return url;
	}

	private static String persistPRoject(final String eventURL, Project proj) throws Exception {

		String url = Manager.sendGet("http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com/project" + "/url/eventURL="
				+ eventURL.replaceAll("/", "%2F") + "/name=" + proj.getName().replaceAll(" ", "%20"));
		if (url != null && !url.trim().equals("")) {

		} else {
			url = Manager.sendPost("http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com/project", proj);
		}
		return url;
	}

	private static String persistEvent(Event o) throws Exception {

		String url = Manager.sendGet("http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com/event" + "/url/name="
				+ o.getName().replaceAll(" ", "%20"));
		if (url != null && !url.trim().equals("")) {

		} else {
			url = Manager.sendPost("http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com/event", o);
		}
		return url;
	}

	private static String persistUSer(User u) throws Exception {

		String url = Manager.sendGet("http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com/user" + "/url/email=" + u.getEmail());
		if (url != null && !url.trim().equals("")) {

		} else {
			url = Manager.sendPost("http://ec2-52-28-69-136.eu-central-1.compute.amazonaws.com/user", u);
		}
		return url;
	}
}
