# Basics of Version Control w/ Git

## Intro 
* "***Version control*** is a system that records changes to a file or set of files over time so that you can recall specific ***versions*** later." (["Getting Started About Version Control"](https://git-scm.com/book/en/v2/Getting-Started-About-Version-Control))

* There are two separate parties in the version control system: 
  * A ***repository*** to record the changes to the project (hidden in the .git folder).<br/>_Note that today it is more likely that "repository" refers to the entire version-controlled project, especially when referring to a project on GitHub._
  * A ***working copy*** (the code you see in eclipse). You make your changes here before committing them.

## Downloading Your Repository (Cloning)
* If you already have a repository online (e.g. GitHub), you will want to ``clone`` the repository. Any repository on GitHub can be cloned with the following format (this URL matches the repository's homepage URL).
```
git clone https://github.com/OU-CS2334/{repository_name}
```
Note that downloading the source code will not allow you to commit to the repository since the source ZIP does not contain the ``.git`` folder, which holds the version control files.

## Committing to a Repository
*In order for these commands to work you must already have a terminal window open in the repository's home directory (if you used clone, this folder should have the same name as the repository).*

### 0. Check the Status of a Repository (`git status`)
* Before doing anything to your repository, be sure that you understand its current state by checking its status:
```
git status
```
* Information included in a `git status`:
  * Which branch of the repository your local copy is using.
  * Whether your local copy is ahead/behind the remote copy
    * These indicate you would need to push/pull, respectively.
  * Any files that **have been** changed but ***have not been*** added to the index
  * Any files that **have been** changed and ***have already been*** added to the index

### 1. Update Git Index (`git add`)
* Since git does not automatically record changes to any particular files, they must be ``add``ed to the git index before they can be committed.
```
git add "src/Diamond.java"
git add "test/*.java"
```

* There is also a wildcard operator for the add command that will automatically pick up all new files (``-A``).
```
git add -A
```

* Since the add command has an unchecked wildcard option, it is possible to ``ignore`` by listing them in the ``.gitignore`` file. For example: the reason you all never have a "bin" folder when you clone the sample repo is because we ignore it. Each ignored file/folder/wildcard should be listed on a separate line.

### 2. Update Local Repository (`git commit`)
* A ``commit`` records the ***change set*** created by the working copy in the repository. After committing, the working copy has no changes and the developer can resume working with a new ***version*** of the project.
  * Remember to check the `status` of your repository before committing any changes to it.
* A commit should always have a brief commit message (``-m``) describing what was changed. At a company, this will likely be an issue ID in the issue-tracking system, where all of the details are stored, but for our purposes you will simply have to be brief in your commit summaries.
```
git commit -m "Implemented & tested new method addTwoNumbers()"
```
* Commits should be ***small*** and should contain one change to a feature of the software (e.g. a bug fix, or adding a new button to a web page). Only commit code that has been tested and is known to work, otherwise the version will not be useful.

### 3. Update Remote Repository (`git push`)
* With modern technologies like GitHub, all git repositories are able to configure a ***remote server*** (also called a "``remote``") which holds their "true" repository. Repositories may have multiple remotes configured. The default git remote is called ``origin``.
```
git push origin
```
* To update the remote you must perform a ``push``. It cannot be committed to directly.
* Conversely, a ``pull`` updates a local repository to match the remote server. Once a push has been done, all users should pull to update their repository before they make changes.

## Recommended Additional Topics
* Working with multiple branches
  * Creating new branches with `git branch` (or software of your choice, but this one is relatively simple)
  * Merging branches with `git merge` (or software of your choice)
  * Handling merge conflicts
    * Much better when done with a handy UI tool
* IDE Integration
  * eclipse - In addition to the Java and Debug perspectives, eclipse also has a perspective for handling common git tasks (and even has GitHub integration).
  * not eclipse - Chances are your IDE of choice has GitHub integration. Try a quick google search and follow the online tutorial for setting it up.
* Forking, especially when using GitHub
