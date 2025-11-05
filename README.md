# 🧠 Smart Pantry — Teammate Onboarding

## 1️⃣ What we’re building
A Java **Swing** app for a housekeeper to manage pantry items: register/login, dashboard (CRUD + consume/restock), search/filters (name/category, expiring ≤ 15 days, low-stock), and an auto shopping list with manual edits + “mark purchased → restock.”

Data is stored in text files and updates after every action.  
We must apply OOP concepts (classes, inheritance, interfaces, polymorphism, collections).  

🗓 **Deadline:** Sunday, Nov 24, 2025 @ 11:59 PM (Asia/Dubai)

## 2️⃣ Repo layout
```markdown
la14l-smart-pantry-app/
├─ src/main/java/pantryAppPackage/
│  ├─ Main.java                 // entry point
│  ├─ MainFrame.java            // main window (JFrame)
│  ├─ LoginPanel.java           // first screen (JPanel)
│  ├─ User.java                 // user entity
│  └─ Item.java                 // pantry item entity
├─ src/main/resources/          // FXML/CSS/images later if needed
├─ data/                        // (create soon) users.txt, items.txt, shopping_lists.txt
├─ .gitignore                   // ignores .idea/, out/, target/
└─ LICENSE, README.md
````
### Data files (plain text)
- **users.txt** – user registration/login records  
- **items.txt** – pantry items, updated after every action  
- **shopping_lists.txt** – shopping lists + entries, updated on change

---

## 3️⃣ Local setup (everyone)
1. **Clone**
   ```bash
   git clone https://github.com/<OWNER>/la14l-smart-pantry-app.git
   cd la14l-smart-pantry-app
   ````

2. **Open in IntelliJ** – open the *project root* (not `src/`)
3. **JDK 17** – *File → Project Structure → Project SDK = 17*
4. **Mark source root (if needed)** – right-click `src/main/java` → *Mark Directory As → Sources Root*
5. **Run** – open `Main.java` → green ▶ *(Run 'Main.main()')*

> 🧩 Don’t commit IDE/build stuff. `.gitignore` should exclude `.idea/`, `*.iml`, `out/`, and `target/`.

---

## 4️⃣ Workflow (branches & PRs)

Small starter tweaks → push to **main**.
New features → use branches + Pull Requests so we don’t collide.

```bash
# get latest
git checkout main
git pull

# start a feature
git checkout -b feature/<short-name>     # e.g., feature/login

# commit work
git add .
git commit -m "feat: add login form layout"

# publish branch
git push -u origin feature/<short-name>
```

Then open a **Pull Request** into `main`, describe briefly (add screenshots if UI).
Teammate reviews → **Squash & merge** to keep history clean.
Later we’ll enable “Require PR + 1 approval” and “Require linear history”.

---

## 5️⃣ Coding style & package structure

For now everything is in `pantryAppPackage`.
Later we’ll split:

```
pantryAppPackage/
  model/     // User, Item, ShoppingList, ShoppingListEntry
  ui/        // JFrame + JPanels (LoginPanel, DashboardPanel, ShoppingListPanel)
  service/   // AuthService, PantryService, ShoppingListService
  storage/   // TextFileStore, Repositories
  util/      // IdGenerator, Validators, DateUtils
  Main.java
```

Keep classes small & single-purpose.
Use interfaces where they help (e.g., `Repository<T>`).

---

## 6️⃣ Minimum features

* **User flow:** register (name, email, phone, password), login, logout; auto IDs; save to `users.txt`.
* **Dashboard:** table (ID, Name, Category, Quantity, Unit, Threshold, Expiry); Add/Edit/Delete/Consume/Restock; instant update → save to `items.txt`.
* **Search & filters:** by name/category; about-to-expire (≤ 15 days); low-stock (qty < threshold).
* **Shopping list:** auto-generate from low-stock; manual add/remove; mark purchased → restock pantry; save to `shopping_lists.txt`.

---

## 7️⃣ Suggested first tasks (create Issues)

* **UI:** `LoginPanel`, `DashboardPanel` (JTable + buttons)
* **Model:** `User`, `Item`, `ShoppingList`, `ShoppingListEntry`
* **Storage:** `TextFileStore`, repositories
* **Services:** `AuthService`, `PantryService`, `ShoppingListService`
* **Validation:** non-negative qty/threshold, valid date, simple email regex

---

## 8️⃣ Swing tips

* One `JFrame` (`MainFrame`), many `JPanel`s for screens
* Use `CardLayout` to switch views
* UI logic → `ui/*`, business logic → `service/*` (no file I/O inside panels)
* After each action → refresh table model → `revalidate(); repaint();`

---

## 9️⃣ Data formats

**users.txt**

```
userId|name|email|phone|passwordHash
U0001|Sara Ali|sara@x.com|0500000000|$2a$10$...
```

**items.txt**

```
itemId|userId|name|category|quantity|unit|threshold|expiry(YYYY-MM-DD)
I0001|U0001|Milk|DAIRY|1.0|LITER|2.0|2025-11-15
```

**shopping_lists.txt**

```
listId|userId|date
entryId|listId|name|quantity|unit|status(NEW/PURCHASED)
L0001|U0001|2025-11-10
E0001|L0001|Milk|2|LITER|NEW
```

---

## 🔟 Demo & grading checklist

* ≥ 2 users; each 15–20 items (low-stock + soon-to-expire); ≥ 1 shopping list per user.
* Demo flow: login → dashboard → search/filters → CRUD → generate list → manual edits → mark purchased → show counts → open files to prove persistence → validation error.
* Grading weights: Functionality 30 % · GUI 15 % · OOP 5 % · Validation 5 % · Report 10 % · Demo/Q&A 35 %.

---

## 11️⃣ Commit messages & PR hygiene

Small, clear commits:

```
feat: add dashboard table and model
fix: correct expiring-soon filter
refactor: extract FileBackedRepository
```

PRs → explain *what & why*, include screenshots for UI, list test steps.

---

## 12️⃣ Common pitfalls

* 🚫 Don’t commit `.idea/`, `out/`, `target/`
* 🚫 Don’t put business logic in UI panels
* ✅ Always pull latest main before new branch:

  ```bash
  git pull origin main
  ```

---

Happy coding 🎉
*Let’s keep commits small, reviews friendly, and the pantry smart!*

```

---

✅ Copy everything inside that code block and paste it directly into your repo’s **README.md** file — GitHub will render it cleanly with headings, code formatting, and emoji.
```
