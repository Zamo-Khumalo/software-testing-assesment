SELECT year(CreationDate) as [Year]
, count(Id)  as CountOfNewUsers
FROM Users where year(CreationDate) >= '2010'
GROUP BY year(CreationDate) 