if( arguments.length == 0 ){
	trace("No file specified");
}else{
	var task = readFile(arguments[0]);
	solve( task );
}
		
function solve(task)
{
		var a = task.split("\n");
		trace(a[0]);
		if( a[ a.length-1].length < 2 ) {
			a.pop();
		}
		var i = 0;
		while( a[i].charAt() == "c" &&  i < a.length ){
			i++;
		}
		var first = a[i];
		
		var numVars = first.split(" ")[2];
		
		i++;
		var clauses = [];
		while( i < a.length ){
			var clause = a[i].split(" ");
			clause.pop();
			trace("Clause:"+clause);
			clauses.push( clause );
			i++;
		}
		
		var solution = [];
		for( var j = 0; j < numVars; j++){
			solution.push(false);
		}
		var result = solveRecur( solution, clauses, 0 );
		
		var resultToWrite = "";
		if( result == null ){
			resultToWrite = "unsatisfieable";
		}else{
			for( var i = 0; i < result.length; i++){
				if( result[i] == true ){
					resultToWrite += ((i+1)+",");
				}else{
					resultToWrite += ("-"+(i+1)+",");
				}
			}
		}
		writeFile( arguments[0]+"_solution", resultToWrite);
}

function solveRecur(solution, clauses, iter)
{
	if( iter == solution.length ){
		return check( solution, clauses );
	}
	solution[iter] = false;
	var result = solveRecur( solution, clauses, iter + 1 );
	if( result != null) {
		return result;
	}
	solution[iter] = true;
	result = solveRecur( solution, clauses, iter + 1 );
	return result;
}

function check(solution, clauses)
{
	for( var i = 0; i < clauses.length; i++){
		var term = clauses[i];
		var termSatisfied = false;
		for( var j = 0; j < term.length; j++ ){
			if( term[j] < 0 ){
				if( solution[ (-term[j])-1 ] == false ){
					termSatisfied = true;
					break;
				}
			}else{
				if( solution[ term[j]-1 ] == true ){
					termSatisfied = true;
					break;
				}
			}
		}
		if( termSatisfied == false ){
			return null;
		}
	}
	return solution;
}