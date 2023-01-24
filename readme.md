> **[EECS 3311]{.underline}**
>
> **[Del-2]{.underline}**

\<GROUP 07\>

**Design Document**

  ------------------------ ----------------------------------------------
  Version:                 1.0.2

  Print Date:              2022-11-12

  Release Date:            2022-11-27

  Release State:           Alpha

  Approval State:          Pending

  Approved by:             

  Prepared by:             Ibrahim, Sahil, Dylan, David

  Reviewed by:             Team

  Path Name:               

  File Name:               

  Document No:             2
  ------------------------ ----------------------------------------------

**Document Change Control**

  ------------- ------------- ------------------ -----------------------------
  **Version**   **Date**      **Authors**        **Summary of Changes**

  0.01          2022-10-12    Dylan, David,      Filling needed aspects(can
                              Ibrahim, Sahil     change this later)

  1.0.2         2022-11-27    Dylan, David,      
                              Ibrahim, Sahil     

                                                 

                                                 
  ------------- ------------- ------------------ -----------------------------

**Document Sign-Off**

  ----------------------------- --------------------------------- ------------
  **Name (Position)**           **Signature**                     **Date**

  Dylan(undefined)              Dylan Smith                       2022-10-01

  Youngwook(David)(undefined)   Youngwook Kim                     2022-10-01

  Ibrahim(undefined)            Ibrahim Mohammed                  2022-10-01

  Sahil Gurparteet(undefined)   Sahil Gurparteet                  2022-10-06
  ----------------------------- --------------------------------- ------------

**Contents**

**1** **Introduction 4**

1.1 Purpose 4

1.2 Overview 4

1.3 Resources - References 4

**2** **Sequence Diagrams 4**

**3** **Major Design Decisions 4**

**4** **Architecture 4**

**5** **Detailed Class Diagrams 4**

5.1 UML Class Diagrams 4

**6** **Use of Design Patterns 4**

**7** **Activities Plan 5**

7.1 Project Backlog and Sprint Backlog 5

7.2 Group Meeting Logs 5

**8** **Test Driven Development 5**

-   **Introduction**

-   **Purpose**

> The purpose of \<StructurallyCorrect 1.0\> is to ultimately create a
> fully functioning system using and implementing many types of
> structural design patterns. This fully functioning system\'s goal is
> to allow the user control of calculating whatever type of data they
> would like upon a country of their choice, of course with some limits.
> Building this system also comes with the purpose of learning about
> structural design patterns and their real-world use.

-   **Overview**

> Brief Overview of the document:
>
> 1\. Sequence Diagrams
>
> 2\. Major Design decisions
>
> 3\. Architecture
>
> 4\. Detailed Class Diagrams
>
> 5\. Activities Plan
>
> 6\. Test-Driven Development

-   **Resources - References**

Online resource of structural design patterns:

[[https://refactoring.guru/design-patterns/structural-patterns]{.underline}](https://refactoring.guru/design-patterns/structural-patterns)

-   **Sequence Diagrams**

> UC1:
>
> The first use case this project requires is the case of a user logging
> into the system. The user is greeted with a login window, where they
> can input their username and password. If this combination is
> incorrect or the user does not exist in the database, an error message
> will pop-up and let the user know. If the combination is correct, the
> main UI of the application will be deployed.
>
> UC2:
>
> The second use case is the first of our use-cases to explain the main
> ui. For us-case 2 the user can select a country from a dropdown menu,
> this country will be used to fetch data for. The system also maintains
> a list of excluded countries. This exclusion list contains countrys
> that are not allowed to be analysed or used for data fetching. Our
> system will check the user selection and evaluate whether or not the
> country is allowed to be used for analysis. Use-case 2 also defines
> that the user must be able to add or remove countries in the
> maintained list of excluded countries. If the selected country is not
> allowed for analysis a message is displayed.
>
> UC3:
>
> The third use-case describes the next functionality of the main ui.
> After the user selects a country that does not exist in the exclusion
> list, the user can now select a specific type of analysis they would
> like to perform. This analysis is contained in a dropdown menu. New
> types of analysis\' must be able to added without affecting the
> existing types, the implementation must be modular. These analyses
> must also be able to be exposed in the future. It must also include
> the functionality of adding and removing analyses\'.
>
> UC4:
>
> The fourth use-case defines the next-step after a successful use-case
> 3. Once the suer has selected a valid analysis type, the user can now
> select a start and end date for there desired data-fetch. There is a
> certain constraint that certain analyses\' types are not available for
> certain years. This is stored on an exclusion list maintained by the
> system. The system must check that the start and end dates are valid
> for the anaysis type and that the start year is before the end date.
> If there is any error, a message is displayed and the user may decide
> again.
>
> UC5&6:
>
> The fifth use-case allows the user to select new graphs which can be
> used to visualize processed data. The user can select a viewer from a
> list maintained by the system. The suer can also add or remove
> viewers. When you select an analysis you always start with an empty
> list of viewers. Up untill this point the user must select a country,
> the years(start and end), typoe of analysis, and the viewer to be
> used.
>
> UC7:
>
> The seventh use-case defines how the user can \'calculate the data\'.
> After the user has selected all the previous required input data, the
> user can click the \'Recalculate\' button. This button will initiate
> the identification and retrieval of the data required for the analysis
> selected. Depending on the analysis type selected, the system may need
> to collect two pieces of data. When this data is collected the system
> may have to do a calculation that includes these pieces. In either
> case(collecting more than one piece of data or a single piece) the
> system wil then need to model the result to send to the selected
> viewers.
>
> UC8:
>
> Once the system completes the analysis computation, the system needs
> to render the results on the selected viewers. Depending on the data
> collected, a single series of data or multiple series of data may be
> displayed. Depending on the analysis, some viewers may not be able to
> be used.

-   **Major Design Decisions**

> *Description of significant design choices, and modularization
> criteria.*
>
> Our major design decisions were heavily influenced by the design
> structures and architectures which EECS 3311 has taught us. We
> followed strategies from the in class labs, and lectures.
>
> We created:
>
> A proxy for our login system
>
> A facade to hold everything together for the mainUI
>
> A factory to create our analysis
>
> A factory to create our views
>
> An observer to manage our views
>
> A command for the mainUI
>
> Interfaces for our views and analysis\'
>
> Singletons in multiple classes

-   **Architecture**

> Brief module breakdown of our system:
>
> Client:
>
> Server:
>
> *-this is our updated component diagram(will include the image in the
> zip file if youwould like to have a better view)*

  ----------------------------------- -----------------------------------
  *Component Name*                    *Description*

  ClientPackage                       The client is where we run our
                                      project. It loads the loginGUI

  LoginPackage                        This is the loginGUI, it holds all
                                      functionality of the login. Uses
                                      the LoginProxyPackage

  ProxyPackage                        This package does the validation
                                      and functions of the loginGUI, it
                                      also uses a proxy design structure
                                      to limit the user from
                                      accessing/viewing the functionality

  MainUIPackage                       The MainUIPackage holds the mainUI
                                      GUI, the MainUIFacade, and the
                                      country json. The mainUI is the hub
                                      of the project, and displays the
                                      views and selections. The facade
                                      follows the design structure and
                                      holds everything together

  UserSelectionPackage                The UserSelectionPackage has the
                                      class UserSelection which is used
                                      as an object to hold all user
                                      selections. This object is then
                                      used to prime the AnalysisFactory,
                                      the views, the result object.

  CommandModulePackage                The CommandModulePackage holds the
                                      command which is called in the
                                      MainUI, when the \'recalculate\'
                                      button is clicked. The command
                                      creates the AnalysisFactory and the
                                      the context.

  AnalysisPackage                     The AnalysisPackage holds all of
                                      the analysis classes that are used.
                                      It also holds the analysisContext
                                      which sets the analysis.

  DataFetcherPackage                  The DataFetcherPackage is used by
                                      the Analysispackage. The analysis
                                      will call the datafetcher which
                                      will get the requested data from
                                      the analysis and return a result
                                      object which can then be used by
                                      the view.

  ResultPackage                       The ResultPackage is used as a
                                      returnable object which holds the
                                      json data which the worldbank api
                                      returns. From this object we can
                                      create datasets and use the data to
                                      create a view on the GUI.

  ViewPackage                         The ViewPackage holds all of our
                                      created views, which are used by
                                      the MainUI when selected by the
                                      user. Each view is a different type
                                      of graph

  FactoryPackage                      The FactoryPackage uses the factory
                                      design pattern. It holds a factory
                                      for the analysis and a seperate
                                      factory for the views.

  UtilitiesPackage                    Contains multiple classes that are
                                      used throughout the system. Used as
                                      helper methods

  HttpModule                          The hub of deliverable 3. We create
                                      a url with our userSelection
                                      parameters and send it to the
                                      server

  AdapterModule                       An adapter for our multiple Apis

  ModifiedRepos                       Updated Repos for deliverable 3

                                      
  ----------------------------------- -----------------------------------

-   **Interface Specification**

-   *A table listing the signatures of the operations contained in each
    > exposed interface of your component diagram as per the table
    > below.*

+--------------------+------------------------------+------------------+
| *Interface Name*   | *Signature of Operations     | *Description of  |
|                    | Exposed in Interface*        | the Operation*   |
+--------------------+------------------------------+------------------+
| *ILogin*           | *Login getInstance()*        | *-returns        |
|                    |                              | singleton        |
|                    | *Login()*                    | instance of      |
|                    |                              | login*           |
|                    |                              |                  |
|                    |                              | *-the actual     |
|                    |                              | creation of the  |
|                    |                              | login gui*       |
+--------------------+------------------------------+------------------+
| *IProxyLogin*      | *ProxyLogin(String, String)* | *-sets the       |
|                    |                              | username,        |
|                    |                              | password*        |
+--------------------+------------------------------+------------------+
|                    | *doValidate()*               | *-validates the  |
|                    |                              | credentials are  |
|                    |                              | correct*         |
+--------------------+------------------------------+------------------+
|                    | launchUI()                   | launches mainUI  |
+--------------------+------------------------------+------------------+
| *IMainUIFacade*    | *getInstance()*              | *-returns        |
|                    |                              | singleton        |
|                    | startMainUI()                | instance*        |
|                    |                              |                  |
|                    | handlers()\....multiple      | *-starts the     |
|                    |                              | mainUI*          |
|                    | recalculateData()            |                  |
|                    |                              | -each handler    |
|                    |                              | handles there    |
|                    |                              | own specific     |
|                    |                              | buttons, whether |
|                    |                              | it be year or    |
|                    |                              | country          |
|                    |                              |                  |
|                    |                              | -calls the       |
|                    |                              | command,         |
|                    |                              | displays the     |
|                    |                              | views etc        |
+--------------------+------------------------------+------------------+
| *IUserSelection*   | *getInstance()*              | *-singleton      |
|                    |                              | instance*        |
|                    | *setters\...getters\...*     |                  |
|                    |                              | *-setters and    |
|                    |                              | getters for      |
|                    |                              | country          |
|                    |                              | ,analysis type,  |
|                    |                              | start to end     |
|                    |                              | years, views,    |
|                    |                              | list of views*   |
+--------------------+------------------------------+------------------+
| *IAnalysisCommand* | *A                           | *-sets the       |
|                    | nalysisCommand(MainUIFacade, | facade,          |
|                    | UserSelection, string)*      | userselection    |
|                    |                              | obj, and         |
|                    | *Result doAction()*          | analysis type*   |
|                    |                              |                  |
|                    |                              | *-creates the    |
|                    |                              | factory, for the |
|                    |                              | specified        |
|                    |                              | analysis, adds   |
|                    |                              | it to the        |
|                    |                              | analysiscontext, |
|                    |                              | returns a result |
|                    |                              | object to the    |
|                    |                              | mainUI*          |
+--------------------+------------------------------+------------------+
| *IAnalysisContext* | *AnalysisContext(Analysis,   | *-sets the       |
|                    | UserSelection)*              | analysis and     |
|                    |                              | userselection*   |
|                    | *execute()*                  |                  |
|                    |                              | *returns the the |
|                    |                              | analysis,        |
|                    |                              | created analysis |
|                    |                              | per the analysis |
|                    |                              | type given*      |
+--------------------+------------------------------+------------------+
| *IDataFetcher*     | *String                      | *-generates the  |
|                    | generateURL(UserSelection)*  | URL needed to    |
|                    |                              | request the data |
|                    | *-HashMap\<Int,              | wanted by the    |
|                    | Double\>fetchData()*         | UserSelection    |
|                    |                              | obj*             |
|                    |                              |                  |
|                    |                              | *-uses the       |
|                    |                              | created URL to   |
|                    |                              | then fetch the   |
|                    |                              | data from the    |
|                    |                              | worldbajnk api,  |
|                    |                              | and return a     |
|                    |                              | HashMap*         |
+--------------------+------------------------------+------------------+
| *IAnalysisFactory* | *Analysis                    | *-creates the    |
|                    | generateAnalysis(String)*    | analysis class   |
|                    |                              | pertaining to    |
|                    |                              | the string       |
|                    |                              | passed into the  |
|                    |                              | function*        |
+--------------------+------------------------------+------------------+
| *IResult*          | *notifyViews()*              | *-for each view  |
|                    |                              | in its list of   |
|                    | *-generateViews()*           | views, it draws  |
|                    |                              | the new data     |
|                    |                              | onto it*         |
|                    |                              |                  |
|                    |                              | *-calls the      |
|                    |                              | viewFactory,     |
|                    |                              | gets the mainUI  |
|                    |                              | instance, and    |
|                    |                              | for each view    |
|                    |                              | requested,       |
|                    |                              | genereates them* |
+--------------------+------------------------------+------------------+
| *IViewFactory*     | *generateView(String)*       | *-generates the  |
|                    |                              | view class from  |
|                    |                              | the string that  |
|                    |                              | is passed into   |
|                    |                              | the class*       |
+--------------------+------------------------------+------------------+
| *IView*            | *draw(Result)*               | *-takes the      |
|                    |                              | Result obj and   |
|                    |                              | uses the data to |
|                    |                              | apply onto the   |
|                    |                              | desired view     |
|                    |                              | graph*           |
+--------------------+------------------------------+------------------+
|                    |                              |                  |
+--------------------+------------------------------+------------------+

-   **Architectural Patterns Used**

> A discussion (two paragraphs in total) of the architectural patterns
> used, and the rationale behind your choice.
>
> Within this deliverable we used the following Architectural patterns:
> singleton, Factory, Proxy, Facade, Strategy, Observer, Command. The
> singleton was used in the MainUI, the LoginUI, the MainFacade, and
> UserSelection for the reason to ensure that we would only have one
> instance at all times. For example the MainUI should\'nt have multiple
> instances, so it uses a singleton. The Factory was used in
> AnalysisFactory and ViewFactory. In both cases it is used to create a
> specific class that implements a greater interface, for the desired
> use. Such as a specific analysis or specific view. It was the most
> logical pattern to create such. The Proxy was used in our Login. The
> Proxy was used to ensure the user or any onlookers would not be able
> to see the full fucntionality of the validation. The Facade structural
> pattern was used to pull aspects from the MainUI, to make it more
> fucntional and so we did not have to contain everything within the
> actual UI class which we thought would clutter it and had to many
> uses. The facade split up the functionality, and allowed us to use
> multiple Interfaces on the UI.
>
> The Strategy behavioral pattern was then used in the AnalysisContext.
> The strategy is to be used when we potenitally have multiple API\'s
> that we may need to fetch data from. But at the moment we only have
> one strategy, which is to use the WorldBank API. The observer
> behavioral pattern is used in our ViewsPackage. Each view is held in a
> list, the list is updated whether the user wants the view or not. Then
> everytime the recalculate button is clicked, each view is notified and
> updated, or added or removed. It is dynamic and the most logical
> structure for the views. The final structure used is the command. The
> command is used when the user clicks recalculate. The command splits
> the creation of the AnalysisContext and AnalysisFactory from the
> facade, and returns a Result object. We used the command as we thought
> it was fitting after lab4. If we were to add other buttons that
> function like recalculate we can use our existing Command interface
> and implement another command for a specific use.

-   **Detailed Class Diagrams**

-   **UML Class Diagrams**

-   **Client**

> Client Package
>
> LoginPackage
>
> MainUIPackage
>
> CommandModulePackage
>
> UserSelectionPackage
>
> FactoryPackage
>
> ResultPackage
>
> ViewsPackage
>
> UtilitiesModule
>
> HttpModule

-   **Server**

analysisModule

> datafetchermodule
>
> factorymodule
>
> httpModule
>
> resultModule

-   **Use of Design Patterns**

> *-Design patterns used:*
>
> *-[Proxy]{.underline}: used in the LoginPackage. To remove visibility
> of the validation from viewers. Concealment.*
>
> *-[Facade]{.underline}: used in the uiModule. We used the facade to
> pull atributes from the GUI. We used it to add functionality to the
> gui and to handle errors. We also used the facade to call the command
> on the recalculate button.*
>
> *-[Factory]{.underline}: used in the factoryModule. The factory design
> pattern was used in two seperate occasions. The first was for
> analysis, and secondly for the view. We have multiple versions or
> types of views and analysis\' so we needed a concrete way to \'build\'
> the correct type of each(analysis, view) per a specific situation. We
> also needed to make sure we could add more types to each, which made
> the factory design pattern even more appealing.*
>
> *-[Observer]{.underline}: used in the viewsModule. The*
>
> Login System
>
> For the login system the major design pattern used is a proxy. We
> decided on the proxy to conceal the main method from the general user.
> We wanted to conceal the complexity of the login validation from the
> user. Proxy was the only structure design pattern used
>
> Analysis System
>
> The first structure used is a factory. This is a more hypothetical use
> as the factory method is used here to \'create' a facade which is the
> second structure used. This facade holds all the complexity of our
> analysis system such as a strategy, viewers, and a user selection
> object. The third structure used is a strategy design pattern. This
> strategy would be responsible of selecting a correct strategy that can
> calculate the correct data needed (upon a data request) and correctly
> \'draw\' the data. These are the three structure design patterns for
> our analysis system at this stage.
>
> Data Fetcher
>
> Our current data fetcher design contains no specific structure design
> patterns at the moment. The viewer is included in the data fetcher
> along with our analysis strategy for ease of viewing. The viewer
> represents a class that will properly display the data given. We once
> again used a factory method to \'create\' the viewer which is a
> implementation of a interface. The \'viewer\' would be whichever is
> best implemented for the given data. This concept will change as we
> better understand how it will be used.

-   **Activities Plan**

-   **Project Backlog and Sprint Backlog**

> *In this Section, and assuming you follow a Scrum process model,
> provide a list of product backlog items so that you can select items
> for your Sprint backlog. Make sure the product backlog list and the
> tasks in each product backlog item are consistent with the Gantt Chart
> in Section 6.1. above.*

-   **Group Meeting Logs**

> In this Section you write minutes of each meeting, listing the
> attendance, what the topics of discussion in the meeting were, any
> decisions that were made, and which team members were assigned which
> tasks. These minutes must be submitted with the project report in each
> deliverable and will provide input to be used for the overall
> assessment of the project.

  --------------- -------------- ----------------------------------------
  **Present Group **Meeting      **Issues Discussed / Resolved**
  Members**       Date**         

  Ibrahim, David, 2022-10-06     Defined the login system proxy and
  Sahil & Dylan                  sequence diagrams. Studied up on
                                 structural designs for the analysis
                                 system. Brief overview of the analysis
                                 use-cases

  Dylan & Sahil   2022-10-10     Login implementation started. Started
                                 putting packages together and first
                                 attempts at creating an analysis system

  Ibrahim, David, 2022-10-12     The group caught up on the login
  Dylan                          implementation ideas and drew up
                                 alterations of analysis system, along
                                 with the sequence diagrams for the
                                 analysis system

  Sahil, Ibrahim, 2022-10-13     Complete rework of the analysis system.
  David & Dylan                  We attended the office hours and
                                 understood that we needed to incorporate
                                 the factory, facade, and strategy into
                                 one streamlined system. This left us
                                 with issues of the viewer though
  --------------- -------------- ----------------------------------------

For version 2.0 development of the project look for Notes.txt for latest
dates for development and java docs (included in the zip file).

Link for GitHub repo will be released once we get our marks ;)

Our initial GAANT Diagram for Del1:

-   **Test Driven Development**

Test cases will be provided in the form of a table as follows:

+-----------------+----------------------------------------------------+
| **Test ID**     | testcase#1                                         |
+-----------------+----------------------------------------------------+
| **Category**    | Evaluation of the Login System(success)            |
+-----------------+----------------------------------------------------+
| **Requirements  | UC1-Successful-User-Login                          |
| Coverage**      |                                                    |
+-----------------+----------------------------------------------------+
| **Initial       | System is initiated and runs                       |
| Condition **    |                                                    |
+-----------------+----------------------------------------------------+
| **Procedure**   | List of steps:                                     |
|                 |                                                    |
|                 | 1\. The user inputs a username                     |
|                 |                                                    |
|                 | 2\. The user inputs a password                     |
|                 |                                                    |
|                 | 3\. The user clicks 'Login'                        |
|                 |                                                    |
|                 | 4\. The login was successful, and the main         |
|                 | application UI is deployed                         |
+-----------------+----------------------------------------------------+
| **Expected      | The login window is closed, and the main           |
| Outcome**       | application UI is shown                            |
+-----------------+----------------------------------------------------+
| **Notes**       | Both the username and password should have no      |
|                 | special characters                                 |
+-----------------+----------------------------------------------------+

+-----------------+----------------------------------------------------+
| **Test ID**     | testcase#2                                         |
+-----------------+----------------------------------------------------+
| **Category**    | Evaluation of the login System(fail)               |
+-----------------+----------------------------------------------------+
| **Requirements  | UC1-unsuccessful-User-Login                        |
| Coverage**      |                                                    |
+-----------------+----------------------------------------------------+
| **Initial       | System is initiated and runs                       |
| Condition **    |                                                    |
+-----------------+----------------------------------------------------+
| **Procedure**   | List of steps:                                     |
|                 |                                                    |
|                 | 1\. The user inputs a username                     |
|                 |                                                    |
|                 | 2\. The user inputs a password                     |
|                 |                                                    |
|                 | 3\. The user clicks 'Login'                        |
|                 |                                                    |
|                 | 4\. The login was unsuccessful, and a notification |
|                 | is shown expressing the issue                      |
+-----------------+----------------------------------------------------+
| **Expected      | The login window remains open, and a notification  |
| Outcome**       | pops up explaining the problem                     |
+-----------------+----------------------------------------------------+
| **Notes**       | Both the username and password should have no      |
|                 | special characters. The username or password is    |
|                 | incorrect.                                         |
+-----------------+----------------------------------------------------+

+-----------------+----------------------------------------------------+
| **Test ID**     | testcase#3                                         |
+-----------------+----------------------------------------------------+
| **Category**    | Evaluation of the Country Selection (Success)      |
+-----------------+----------------------------------------------------+
| **Requirements  | UC2-Successful-Country-Selection                   |
| Coverage**      |                                                    |
+-----------------+----------------------------------------------------+
| **Initial       | System is initiated and runs/login was successful  |
| Condition **    |                                                    |
+-----------------+----------------------------------------------------+
| **Procedure**   | List of steps:                                     |
|                 |                                                    |
|                 | 1\. The user accesses Country dropdown             |
|                 |                                                    |
|                 | 2\. The user selects desired country               |
|                 |                                                    |
|                 | 3\. The desired country is allowed for analysis    |
|                 | and not included in the exclusion list             |
+-----------------+----------------------------------------------------+
| **Expected      | The application proceeds and the user is able to   |
| Outcome**       | move onto the next selection                       |
+-----------------+----------------------------------------------------+
| **Notes**       |                                                    |
+-----------------+----------------------------------------------------+

+-----------------+----------------------------------------------------+
| **Test ID**     | testcase#4                                         |
+-----------------+----------------------------------------------------+
| **Category**    | Evaluation of the Country Selection(Failure)       |
+-----------------+----------------------------------------------------+
| **Requirements  | UC2-Unsuccessful-Country-Selection                 |
| Coverage**      |                                                    |
+-----------------+----------------------------------------------------+
| **Initial       | System is initiated and runs/login was successful  |
| Condition **    |                                                    |
+-----------------+----------------------------------------------------+
| **Procedure**   | List of steps:                                     |
|                 |                                                    |
|                 | 1\. The user accesses Country dropdown             |
|                 |                                                    |
|                 | 2\. The user selects a desired country             |
|                 |                                                    |
|                 | 3\. The desired country is not allowed for         |
|                 | analysis and is included in the exclusion list     |
+-----------------+----------------------------------------------------+
| **Expected      | An error message stating \'the selected country is |
| Outcome**       | not allowed for analysis\' pops up and the user    |
|                 | must select an allowed country to proceed          |
+-----------------+----------------------------------------------------+
| **Notes**       |                                                    |
+-----------------+----------------------------------------------------+

+-----------------+----------------------------------------------------+
| **Test ID**     | testcase#5                                         |
+-----------------+----------------------------------------------------+
| **Category**    | Evaluation of the Analysis Type Selection(Success) |
+-----------------+----------------------------------------------------+
| **Requirements  | UC3-Successful-Analysis-Type-Selection             |
| Coverage**      |                                                    |
+-----------------+----------------------------------------------------+
| **Initial       | System is initiated and runs/Selected country was  |
| Condition **    | allowed                                            |
+-----------------+----------------------------------------------------+
| **Procedure**   | List of steps:                                     |
|                 |                                                    |
|                 | 1\. The user accesses dropdown                     |
|                 |                                                    |
|                 | 2\. The user selects analysis type                 |
|                 |                                                    |
|                 | 3\. The selection was successful, and the values   |
|                 | for analysis types are saved                       |
+-----------------+----------------------------------------------------+
| **Expected      | The Analysis window remains open, and the user can |
| Outcome**       | proceed to analysis year selection                 |
+-----------------+----------------------------------------------------+
| **Notes**       | There would be no failure case for the Analysis    |
|                 | Type Selection as there is no specific condition   |
|                 | required for the selection                         |
+-----------------+----------------------------------------------------+

+-----------------+----------------------------------------------------+
| **Test ID**     | testcase#6                                         |
+-----------------+----------------------------------------------------+
| **Category**    | Evaluation of the Analysis Type Selection(Failure) |
+-----------------+----------------------------------------------------+
| **Requirements  | UC3-Unsuccessful-Analysis-Type-Selection           |
| Coverage**      |                                                    |
+-----------------+----------------------------------------------------+
| **Initial       | System is initiated and runs/Selected country was  |
| Condition **    | allowed                                            |
+-----------------+----------------------------------------------------+
| **Procedure**   | List of steps:                                     |
|                 |                                                    |
|                 | 1\. The user accesses dropdown                     |
|                 |                                                    |
|                 | 2\. The user selects analysis type                 |
|                 |                                                    |
|                 | 3\. The selection was unsuccessful                 |
+-----------------+----------------------------------------------------+
| **Expected      | The Analysis window remains open and an error      |
| Outcome**       | message pops up stating \'the selected analysis    |
|                 | per the selected country is not available, please  |
|                 | select a different analysis\'                      |
+-----------------+----------------------------------------------------+
| **Notes**       |                                                    |
+-----------------+----------------------------------------------------+

+-----------------+----------------------------------------------------+
| **Test ID**     | testcase#7                                         |
+-----------------+----------------------------------------------------+
| **Category**    | Evaluation of the choice of the Analysis           |
|                 | Years(fail)                                        |
+-----------------+----------------------------------------------------+
| **Requirements  | UC4-unsuccessful-Year-Selection                    |
| Coverage**      |                                                    |
+-----------------+----------------------------------------------------+
| **Initial       | System is initiated and runs                       |
| Condition **    |                                                    |
+-----------------+----------------------------------------------------+
| **Procedure**   | List of steps:                                     |
|                 |                                                    |
|                 | 1\. The user accesses dropdown                     |
|                 |                                                    |
|                 | 2\. The user selects analysis years                |
|                 |                                                    |
|                 | 3\. The selection was unsuccessful, and a          |
|                 | notification is shown expressing the issue         |
+-----------------+----------------------------------------------------+
| **Expected      | The Analysis window remains open, and a            |
| Outcome**       | notification pops up asking for valid selection of |
|                 | years                                              |
+-----------------+----------------------------------------------------+
| **Notes**       | Start year can\'t be greater than end year.        |
|                 | Certain years are not compatible with specific     |
|                 | analysis types                                     |
+-----------------+----------------------------------------------------+

+-----------------+----------------------------------------------------+
| **Test ID**     | testcase#8                                         |
+-----------------+----------------------------------------------------+
| **Category**    | Evaluation of the choice of the Analysis           |
|                 | years(Success)                                     |
+-----------------+----------------------------------------------------+
| **Requirements  | UC4-successful-year-Selection                      |
| Coverage**      |                                                    |
+-----------------+----------------------------------------------------+
| **Initial       | System is initiated and runs                       |
| Condition **    |                                                    |
+-----------------+----------------------------------------------------+
| **Procedure**   | List of steps:                                     |
|                 |                                                    |
|                 | 1\. The user accesses dropdown                     |
|                 |                                                    |
|                 | 2\. The user selects analysis years                |
|                 |                                                    |
|                 | 3\. The selection was successful, and the user     |
|                 | proceeds to next selection                         |
+-----------------+----------------------------------------------------+
| **Expected      | The Analysis window remains open, and the user can |
| Outcome**       | proceed to available view selection                |
+-----------------+----------------------------------------------------+
| **Notes**       | Start year can\'t be greater than end year.        |
|                 | Certain years are not compatible with specific     |
|                 | analysis types.                                    |
+-----------------+----------------------------------------------------+

+-----------------+----------------------------------------------------+
| **Test ID**     | testcase#9                                         |
+-----------------+----------------------------------------------------+
| **Category**    | Evaluation of the viewer selection(pass)           |
+-----------------+----------------------------------------------------+
| **Requirements  | UC5-successful-add-viewer                          |
| Coverage**      |                                                    |
+-----------------+----------------------------------------------------+
| **Initial       | User was able to select a year                     |
| Condition **    |                                                    |
+-----------------+----------------------------------------------------+
| **Procedure**   | List of steps:                                     |
|                 |                                                    |
|                 | 1\. The user accesses viewer dropdown list         |
|                 |                                                    |
|                 | 2\. The user clicks the viewer they would like     |
|                 | from the dropdown                                  |
|                 |                                                    |
|                 | 3\. The user clicks the option to add viewer to    |
|                 | the viewport                                       |
|                 |                                                    |
|                 | 4\. The selection was allowed                      |
+-----------------+----------------------------------------------------+
| **Expected      | The selected viewer is added to the users viewport |
| Outcome**       |                                                    |
+-----------------+----------------------------------------------------+
| **Notes**       | The selected viewer should not be previously       |
|                 | selected. The user should have a country and       |
|                 | correct years selected prior.                      |
+-----------------+----------------------------------------------------+

+-----------------+----------------------------------------------------+
| **Test ID**     | testcase#10                                        |
+-----------------+----------------------------------------------------+
| **Category**    | Evaluation of the viewer selection(fail)           |
+-----------------+----------------------------------------------------+
| **Requirements  | UC5-Unsuccessful-add-viewer                        |
| Coverage**      |                                                    |
+-----------------+----------------------------------------------------+
| **Initial       | User has selected a country and suitable start/end |
| Condition **    | years                                              |
+-----------------+----------------------------------------------------+
| **Procedure**   | List of steps:                                     |
|                 |                                                    |
|                 | 1\. The user accesses the viewer dropdown list     |
|                 |                                                    |
|                 | 2\. The user clicks the viewer they would like     |
|                 | from the dropdown menu                             |
|                 |                                                    |
|                 | 3\. The user clicks the option to add viewer to    |
|                 | the viewport                                       |
|                 |                                                    |
|                 | 4\. The viewer selection type does not pair with   |
|                 | the selected analysis type                         |
+-----------------+----------------------------------------------------+
| **Expected      | The selected viewer is not added to the viewport   |
| Outcome**       | and an error message indicating that the selected  |
|                 | viewer, analysis combination is invalid            |
+-----------------+----------------------------------------------------+
| **Notes**       | The suer has selected the desired country and      |
|                 | start/end years prior to this case                 |
+-----------------+----------------------------------------------------+

+-----------------+----------------------------------------------------+
| **Test ID**     | testcase#11                                        |
+-----------------+----------------------------------------------------+
| **Category**    | Evaluation of the viewer selection                 |
+-----------------+----------------------------------------------------+
| **Requirements  | UC6-Successful-remove-viewer                       |
| Coverage**      |                                                    |
+-----------------+----------------------------------------------------+
| **Initial       | User has selected a country and start/end years    |
| Condition **    |                                                    |
+-----------------+----------------------------------------------------+
| **Procedure**   | List of steps:                                     |
|                 |                                                    |
|                 | 1\. The user access the viewer dropdown list       |
|                 |                                                    |
|                 | 2\. The user clicks the viewer they would like to  |
|                 | remove                                             |
|                 |                                                    |
|                 | 3\. The user clicks remove viewer                  |
|                 |                                                    |
|                 | 4\. The selected was allowed                       |
+-----------------+----------------------------------------------------+
| **Expected      | The selected viewer was allowed to be removed from |
| Outcome**       | the viewport.                                      |
+-----------------+----------------------------------------------------+
| **Notes**       | The viewer had existed in the viewport             |
+-----------------+----------------------------------------------------+

+-----------------+----------------------------------------------------+
| **Test ID**     | testcase#12                                        |
+-----------------+----------------------------------------------------+
| **Category**    | Evaluation of the viewer selection                 |
+-----------------+----------------------------------------------------+
| **Requirements  | UC6-Unsuccessful-remove-viewer                     |
| Coverage**      |                                                    |
+-----------------+----------------------------------------------------+
| **Initial       | User has selected a country and start/end years    |
| Condition **    |                                                    |
+-----------------+----------------------------------------------------+
| **Procedure**   | List of steps:                                     |
|                 |                                                    |
|                 | 1\. The user accesses the viewer dropdown list     |
|                 |                                                    |
|                 | 2\. The user clicks the viewer they would like to  |
|                 | remove                                             |
|                 |                                                    |
|                 | 3\. The user clicks remove viewer                  |
|                 |                                                    |
|                 | 4\. The selection was invalid                      |
+-----------------+----------------------------------------------------+
| **Expected      | The user is prompted with an error message         |
| Outcome**       | indicating that the viewer selected for removal is |
|                 | invalid                                            |
+-----------------+----------------------------------------------------+
| **Notes**       |                                                    |
+-----------------+----------------------------------------------------+

+------------+---------------------------------------------------------+
| Test ID    | UserCase 7.1/testcase #13                               |
+------------+---------------------------------------------------------+
| Category   | Performing Analysis                                     |
+------------+---------------------------------------------------------+
| Re         | *UC7- Analysis: Implementation of Data Fetcher*         |
| quirements |                                                         |
| Coverage   |                                                         |
+------------+---------------------------------------------------------+
| Initial    | Initial conditions required for the test case to run :  |
| Condition  |                                                         |
|            | Type of computation required in Analysis. (e.g Annual % |
|            | change )                                                |
+------------+---------------------------------------------------------+
| Procedure  | The list of steps required for this test case (*e.g.*   |
|            |                                                         |
|            | *1. The user selects login*                             |
|            |                                                         |
|            | *2. The user provides a user name*                      |
|            |                                                         |
|            | *3. The user provides a password *                      |
|            |                                                         |
|            | *4. The user logs-in into the system and is presented   |
|            | with the main UI window*)                               |
|            |                                                         |
|            | 5.User selection from GUI and evolution for possible    |
|            | combination of country, dates and analysis.             |
|            |                                                         |
|            | 6\. Once "recalculate" is pressed. The object is        |
|            | created for Analysis type and data fetcher is called.   |
+------------+---------------------------------------------------------+
| **Expected | HashMap\<Integer year, Integer value) is expected from  |
| Outcome**  | result Data from the data fetchers.                     |
|            |                                                         |
|            | For Annual percentage type computation the return type  |
|            | of the Hashmap shall have starting value of year shall  |
|            | be (startYear -- 1 ) {startYear: from user selection}   |
+------------+---------------------------------------------------------+
| **Notes**  |                                                         |
+------------+---------------------------------------------------------+

+-------------+--------------------------------------------------------+
| Test ID     | UserCase 7.2 /testcase 14                              |
+-------------+--------------------------------------------------------+
| Category    | Performing Analysis                                    |
+-------------+--------------------------------------------------------+
| R           | *UC7- Analysis: Implementation of Data Fetcher*        |
| equirements |                                                        |
| Coverage    |                                                        |
+-------------+--------------------------------------------------------+
| Initial     | Initial conditions required for the test case to run : |
| Condition   |                                                        |
|             | Type of computation required in Analysis. (e.g Ratio ) |
+-------------+--------------------------------------------------------+
| Procedure   | The list of steps required for this test case (*e.g.*  |
|             |                                                        |
|             | *1. The user selects login*                            |
|             |                                                        |
|             | *2. The user provides a user name*                     |
|             |                                                        |
|             | *3. The user provides a password *                     |
|             |                                                        |
|             | *4. The user logs-in into the system and is presented  |
|             | with the main UI window*)                              |
|             |                                                        |
|             | 5.User selection from GUI and evolution for possible   |
|             | combination of country, dates and analysis.            |
|             |                                                        |
|             | 6\. Once "recalculate" is pressed. The object is       |
|             | created for Analysis type and data fetcher is called.  |
+-------------+--------------------------------------------------------+
| **Expected  | HashMap\<Integer year, Integer value) is expected from |
| Outcome**   | result Data from the data fetchers.                    |
|             |                                                        |
|             | For Ratio type computation the return type of the      |
|             | Hashmap shall have starting value of year shall be     |
|             | (startYear ) {startYear: from user selection} and      |
|             | returns a double ratio                                 |
+-------------+--------------------------------------------------------+
| **Notes**   | No need to get (startYear -1 ) for Ration data types.  |
+-------------+--------------------------------------------------------+
| **Test ID** | UserCase 8.1                                           |
+-------------+--------------------------------------------------------+
| *           | Displaying Result                                      |
| *Category** |                                                        |
+-------------+--------------------------------------------------------+
| **R         | UC8- Displaying: Converting resultData to a viewerData |
| equirements | for Diaplaying to GUI                                  |
| Coverage**  |                                                        |
+-------------+--------------------------------------------------------+
| **Initial   | Initial conditions required for the test case to run : |
| C           |                                                        |
| ondition ** | For 3-series computation need 3 viewerData objects for |
|             | different view type(XY plot or Scatter Plot)           |
+-------------+--------------------------------------------------------+
| **          | The list of steps required for this test case (e.g.    |
| Procedure** |                                                        |
|             | 1\. The user selects login                             |
|             |                                                        |
|             | 2\. The user provides a user name                      |
|             |                                                        |
|             | 3\. The user provides a password                       |
|             |                                                        |
|             | 4\. The user logs-in into the system and is presented  |
|             | with the main UI window)                               |
|             |                                                        |
|             | 5.User selection from GUI and evolution for possible   |
|             | combination of country, dates and analysis.            |
|             |                                                        |
|             | 6\. Once "recalculate" is pressed. The object is       |
|             | created for Analysis type and data fetcher is called.  |
|             |                                                        |
|             | 7.Fetched data needs computation and after computation |
|             | resultData needs to be converted into object type --   |
|             | viewerData                                             |
+-------------+--------------------------------------------------------+
| **Expected  | HashMap\<Integer year, Integer value) is expected from |
| Outcome**   | result Data from the data fetchers.                    |
|             |                                                        |
|             | For Ratio type computation the return type of the      |
|             | Hashmap shall have starting value of year shall be     |
|             | (startYear ) {startYear: from user selection} and      |
|             | returns a double ratio                                 |
+-------------+--------------------------------------------------------+
| **Notes**   | No futher implementation reached.                      |
+-------------+--------------------------------------------------------+
