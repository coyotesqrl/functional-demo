### BoilerplateRemoval

##### `setEmployeeDates`
```java
    static class FuncMapper {
        private final Function<Employee, Date> dateGetter;

        private final BiConsumer<Employee, Date> dateSetter;

        FuncMapper(Function<Employee, Date> dateGetter, BiConsumer<Employee, Date> dateSetter) {
            this.dateGetter = dateGetter;
            this.dateSetter = dateSetter;
        }
    }

    private static final Map<String, FuncMapper> DATE_FUNCS = new HashMap<>();

    static {
        DATE_FUNCS.put("HIRE", new FuncMapper(Employee::getHireDate, Employee::setHireDate));
        DATE_FUNCS.put("START", new FuncMapper(Employee::getStartDate, Employee::setStartDate));
        DATE_FUNCS.put("REVIEW",
                new FuncMapper(Employee::getLastReviewDate, Employee::setLastReviewDate));
    }
```

```java
        for (Map.Entry<String, Date> row : dates.entrySet()) {
            FuncMapper funcMapper = DATE_FUNCS.get(row.getKey());
            if (funcMapper.dateGetter.apply(employee) != null) {
                System.out.printf("Existing value present for %s date: %s%n",
                        row.getKey(),
                        funcMapper.dateGetter.apply(employee)
                                .toString());
            }
            funcMapper.dateSetter.accept(employee, row.getValue());
        }
```
##### `addManagerNameToAttributes`, `addSubordNamesToAttributes`, and `addDepartmentNameToAttributes`
```java
public void addEmployeeAttribute(Employee employee,
        Function<Employee, Serializable> attrValFunc, String attrName, boolean multi,
        AttributeType type) {
    Optional.ofNullable(attrValFunc.apply(employee))
            .ifPresent(val -> {
                Attribute attribute = new Attribute(type, multi, attrName);
                attribute.setValue(val);
                employee.addAttribute(attribute);
            });
}
```
```java    
public void addManagerNameToAttributes(Employee employee) {
    addEmployeeAttribute(employee,
            e -> e.getManager()
                    .getName(),
            "MGR_NAME",
            false,
            STR_TYPE);
}
```
```java
public void addSubordNamesToAttributes(Employee employee) {
    addEmployeeAttribute(employee, e -> {
        if (e.getDepartment().getManager().equals(e)) {
            return null;
        }
        return e.getDepartment().getEmployees().stream()
                .filter(sub -> !sub.equals(e))
                .map(Employee::getName)
                .collect(Collectors.toCollection(ArrayList::new));
    }, "SUBORD_NAME", true, STR_TYPE);
}
```
```java
public void addDepartmentNameToAttributes(Employee employee) {
    addEmployeeAttribute(employee,
            e -> e.getDepartment()
                    .getName(),
            "DEPT_NAME",
            false,
            STR_TYPE);
}
```

### Comparisons

##### `sortEmployeesHighesttoLowestSalary`
```java
        return departments.stream()
                .flatMap(d -> d.getEmployees().stream())
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .collect(Collectors.toList());
```

##### `sortedSecurityReleasability`
```java
    private final Comparator<String> securityComp = ((Comparator<String>) (o1, o2) -> {
        if (o1.equals("USA")) {
            return -1;
        }
        if (o2.equals("USA")) {
            return 1;
        }
        return 0;
    }).thenComparingInt(String::length)
            .thenComparing(String::compareTo);
```
```java            
    public String sortedSecurityReleasability(String input) {
        if (input == null) {
            return "";
        }

        return Pattern.compile(",")
                .splitAsStream(input)
                .map(String::trim)
                .filter(s -> s.length() == 3 || s.length() == 4)
                .sorted(securityComp)
                .collect(Collectors.joining(","));
    }
```

### SimpleCollectors

##### `getEmployeeAttribute`
```java
        return Stream.of(department)
                .flatMap(d -> d.getEmployees().stream())
                .filter(e -> e.getAttribute(attrName) != null)
                .collect(Collectors.toMap(Function.identity(), e -> e.getAttribute(attrName)));
```

##### `getEmployeeAttributes`
```java
        return Stream.of(department)
                .flatMap(d -> d.getEmployees().stream())
                .filter(e -> e.getAttributeNames() != null)
                .collect(Collectors.toMap(Function.identity(), e ->
                    attrNames.stream()
                            .map(e::getAttribute)
                            .collect(Collectors.toList())
                ));
```

### SimpleReduction

##### `getMaxSalary`
```java
public int getMaxSalary(Department department) {
    return Stream.of(department)
            .filter(Objects::nonNull)
            .map(Department::getEmployees)
            .filter(Objects::nonNull)
            .flatMap(Collection::stream)
            .mapToInt(Employee::getSalary)
            .max()
            .orElse(0);
}
```
##### `getNumEmployees`
```java
public int getNumEmployees(List<Department> departments) {
    return departments.stream()
            .map(Department::getEmployees)
            .mapToInt(List::size)
            .sum();
```

##### `getEmployeeTotalRanking`
```java
public int getEmployeeTotalRanking(Employee employee, int year) {
    employee.getDepartment()
            .getEmployeeReviews(employee)
            .stream()
            .filter(r -> r.getReviewYear() == year)
            .flatMap(r -> r.getQuestionRankings()
                    .values()
                    .stream())
            .mapToInt(v -> v)
            .sum();
}
```