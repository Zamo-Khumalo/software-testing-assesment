SELECT COUNT(Posts.iD)
FROM Posts 
LEFT JOIN PostTypes ON Posts.PostTypeId=PostTypes.Id
where PostTypes.Name ='Question' AND Posts.OwnerDisplayName ='Mat Mannion';