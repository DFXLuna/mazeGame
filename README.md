

# How to use Git/Github

1. Install Git [Windows](https://git-for-windows.github.io/), [Linux/Mac](http://git-scm.com/book/en/v2/Getting-Started-Installing-Git).
2. Find Eclipse's workspace folder. You specified this.
3. Fork the original repo using Githubs web interface
4. Set up syncing between your fork and the original repo. Check syncing.
5. Clone the repo into your workspace folder. Follow this guide [Cloning a repo](https://help.github.com/articles/cloning-a-repository/).
6. Import project into Eclipse using Import>General>Existing Project
7. Make changes.
8. Send changes to server. (This only changes your fork).
9. Make pull request. [Pull request guide](https://help.github.com/articles/using-pull-requests/)


# Syncing

To check your current upstream branch.
```
git remote -v
```
To sync to the original repo.
```
git remote add upstream <link to *this* repo>
```
Verify upstream branch.
```
git remote -v
```

# Send changes to server

Add files to staging area.
```
git add *
```
Commit to local repo.
```
git commit -m "Clear indiciator of exactly what you are commiting"
```
Push to remote server.
```
git push origin master
```

# Links
### Front End Dev
[Working with Frames](https://docs.oracle.com/javase/tutorial/uiswing/components/frame.html)

[How to use GridBagLayout](https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html)

[GridBagLayout Example](https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/GridBagLayoutDemoProject/src/layout/GridBagLayoutDemo.java)


###UML
[Gliffy](https://www.gliffy.com/go/html5/10049449)
