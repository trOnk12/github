package com.example.github.data.network.tools;

import javax.inject.Inject;

import static com.example.github.data.network.tools.IGitHubConstants.HEADER_LAST;
import static com.example.github.data.network.tools.IGitHubConstants.HEADER_NEXT;
import static com.example.github.data.network.tools.IGitHubConstants.META_FIRST;
import static com.example.github.data.network.tools.IGitHubConstants.META_LAST;
import static com.example.github.data.network.tools.IGitHubConstants.META_NEXT;
import static com.example.github.data.network.tools.IGitHubConstants.META_PREV;
import static com.example.github.data.network.tools.IGitHubConstants.META_REL;

public class PageLinks {

	private static final String DELIM_LINKS = ","; //$NON-NLS-1$

	private static final String DELIM_LINK_PARAM = ";"; //$NON-NLS-1$

	private String first;
	private String last;
	private String next;
	private String prev;


	public PageLinks(String linkHeader) {
		if (linkHeader != null) {
			String[] links = linkHeader.split(DELIM_LINKS);
			for (String link : links) {
				String[] segments = link.split(DELIM_LINK_PARAM);
				if (segments.length < 2)
					continue;

				String linkPart = segments[0].trim();
				if (!linkPart.startsWith("<") || !linkPart.endsWith(">")) //$NON-NLS-1$ //$NON-NLS-2$
					continue;
				linkPart = linkPart.substring(1, linkPart.length() - 1);

				for (int i = 1; i < segments.length; i++) {
					String[] rel = segments[i].trim().split("="); //$NON-NLS-1$
					if (rel.length < 2 || !META_REL.equals(rel[0]))
						continue;

					String relValue = rel[1];
					if (relValue.startsWith("\"") && relValue.endsWith("\"")) //$NON-NLS-1$ //$NON-NLS-2$
						relValue = relValue.substring(1, relValue.length() - 1);

					switch (relValue) {
						case META_FIRST:
							first = linkPart;
							break;
						case META_LAST:
							last = linkPart;
							break;
						case META_NEXT:
							next = linkPart;
							break;
						case META_PREV:
							prev = linkPart;
							break;
					}
				}
			}
		}
	}

	/**
	 * @return first
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * @return last
	 */
	public String getLast() {
		return last;
	}

	/**
	 * @return next
	 */
	public String getNext() {
		return next;
	}

	/**
	 * @return prev
	 */
	public String getPrev() {
		return prev;
	}
}